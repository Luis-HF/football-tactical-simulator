function requireAuth() {
  if (!localStorage.getItem("auth")) {
    window.location.href = "login.html";
  }
}

document.addEventListener("DOMContentLoaded", () => {
  if (location.pathname.includes("game.html")) {
    requireAuth();
  }
});
