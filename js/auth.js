document.getElementById('registerForm').onsubmit = async (e) => {
    e.preventDefault();

    const payload = {
        username: document.getElementById('username').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    };

    const res = await fetch('http://localhost:8080/api/v1/accounts', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    if (res.ok) window.location.href = "game.html";
    else alert("Erro: Verifique os dados ou o servidor.");
};