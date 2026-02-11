import http from 'k6/http';
import { check } from 'k6';

export const options = {
    vus: 1,
    iterations: 50, // Padrão de aquecimento estabelecido
};

export default function () {
    const url = 'http://localhost:8080/api/v1/accounts';

    // Gerador de ID curto para respeitar o @Size(max=20) do seu DTO
    const shortId = Date.now().toString().slice(-8) + Math.floor(Math.random() * 100);

    const payload = JSON.stringify({
        username: `u_${shortId}`,
        email: `w_${shortId}@test.com`,
        password: "strong_password_123"
    });

    const params = {
        headers: { 'Content-Type': 'application/json' },
    };

    const res = http.post(url, payload, params);

    check(res, {
        'status is 201': (r) => r.status === 201,
    });
}