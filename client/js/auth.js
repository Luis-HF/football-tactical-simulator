// ========================================
// LOGIN
// ========================================

const loginForm = document.getElementById("loginForm");

if (loginForm) {

  const button = loginForm.querySelector(".submit-btn");
  const spinner = loginForm.querySelector(".spinner");
  const text = loginForm.querySelector(".btn-text");

  loginForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    button.disabled = true;
    spinner.classList.remove("hidden");
    text.textContent = "Entrando...";

    try {

      const response = await fetch("http://localhost:8080/api/v1/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
      });

      if (!response.ok) {
        alert("Invalid email or password");
        return;
      }

      const data = await response.json();

      // ✅ SALVA SESSÃO
      localStorage.setItem("userId", data.id);
      localStorage.setItem("userName", data.username);

      window.location.href = "game.html";

    } catch (error) {
      alert("Unable to connect to server.");
    }

    button.disabled = false;
    spinner.classList.add("hidden");
    text.textContent = "Entrar";
  });
}



// ========================================
// REGISTER
// ========================================

const registerForm = document.getElementById("registerForm");

if (registerForm) {

  const button = registerForm.querySelector(".submit-btn");
  const spinner = registerForm.querySelector(".spinner");
  const text = registerForm.querySelector(".btn-text");

  registerForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    button.disabled = true;
    spinner.classList.remove("hidden");
    text.textContent = "Cadastrando...";

    try {

      const response = await fetch("http://localhost:8080/api/v1/accounts", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ username: name, email, password })
      });

      if (!response.ok) {
        alert("User or password invalid.");
        return;
      }

      alert("Cadastro realizado com sucesso!");
      window.location.href = "login.html";

    } catch (error) {
      alert("Server unstable, please try again in a few moments.");
    }

    button.disabled = false;
    spinner.classList.add("hidden");
    text.textContent = "Cadastrar";
  });
}



// ========================================
// LOGOUT
// ========================================

const logoutBtn = document.getElementById("logoutBtn");

if (logoutBtn) {
  logoutBtn.addEventListener("click", function () {
    localStorage.clear();
    window.location.href = "index.html";
  });
}



// ========================================
// PROTEÇÃO DE ROTA (GAME PAGE)
// ========================================

const protectedPage = window.location.pathname.includes("game.html");

if (protectedPage) {
  const userId = localStorage.getItem("userId");

  if (!userId) {
    window.location.href = "login.html";
  }
}
