document.addEventListener("DOMContentLoaded", () => {

  const actions = document.getElementById("actions");

  renderMainMenu();

  /* =========================
     UTIL
  ========================= */

  function clear() {
    actions.innerHTML = "";
    actions.style.display = "flex";
    actions.style.gap = "20px";
    actions.style.justifyContent = "center";
    actions.style.flexDirection = "row";
  }

  function createButton(text, callback) {
    const btn = document.createElement("button");
    btn.textContent = text;
    btn.classList.add("btn");
    btn.addEventListener("click", callback);
    return btn;
  }

  function createCard(title) {
    clear();
    actions.style.flexDirection = "column";

    const card = document.createElement("div");
    card.classList.add("card");

    const h2 = document.createElement("h2");
    h2.textContent = title;

    card.appendChild(h2);
    actions.appendChild(card);

    return card;
  }

  /* =========================
     MENU PRINCIPAL
  ========================= */

  function renderMainMenu() {
    clear();
    const playBtn = createButton("Play", renderModeSelection);
    actions.appendChild(playBtn);
  }

  /* =========================
     ESCOLHER MODO
  ========================= */

  function renderModeSelection() {
    clear();

    const createBtn = createButton("Criar Sala", renderCreateRoom);
    const joinBtn = createButton("Entrar na Sala", renderLobby);

    actions.append(createBtn, joinBtn);
  }

  /* =========================
     CRIAR SALA
  ========================= */

  function renderCreateRoom() {
    const card = createCard("Criar Sala");

    const nameInput = document.createElement("input");
    nameInput.placeholder = "Nome da partida";
    nameInput.classList.add("input");

    const passInput = document.createElement("input");
    passInput.type = "password";
    passInput.placeholder = "Senha (opcional)";
    passInput.classList.add("input");

    const createBtn = createButton("Criar Partida", () => {
      alert("Sala criada (protótipo)");
    });

    const backBtn = createButton("Voltar", renderModeSelection);

    card.append(nameInput, passInput, createBtn, backBtn);
  }

  /* =========================
     LOBBY
  ========================= */

  function renderLobby() {
    const card = createCard("Lobby");

    const searchInput = document.createElement("input");
    searchInput.placeholder = "Buscar partida...";
    searchInput.classList.add("input");

    const roomList = document.createElement("div");
    roomList.classList.add("room-list");

    ["Sala 1", "Sala 2", "Sala 3"].forEach(room => {
      const p = document.createElement("p");
      p.textContent = room;
      roomList.appendChild(p);
    });

    const enterBtn = createButton("Entrar", () => {
      alert("Entrando na sala (protótipo)");
    });

    const backBtn = createButton("Voltar", renderModeSelection);

    card.append(searchInput, roomList, enterBtn, backBtn);
  }

});
