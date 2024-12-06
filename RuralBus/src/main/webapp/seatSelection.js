function getViagemIdURL() {
    const params = new URLSearchParams(window.location.search);
    return params.get("idViagem");
}

document.addEventListener("DOMContentLoaded", () => {
    const seatSelect = document.getElementById("seatNumber");
    const idViagem = 53;

    fetch(`http://localhost:8080/RuralBus/assentosOcupados.do?viagemId=${idViagem}`)
        .then(response => response.json())
        .then(occupiedSeats => {
            for (let i = 1; i <= 24; i++) {
                if (!occupiedSeats.includes(i)) {
                    const option = document.createElement("option");
                    option.value = i;
                    option.textContent = `Assento ${i}`;
                    seatSelect.appendChild(option);
                }
            }
        })
        .catch(error => console.error("Erro ao carregar assentos ocupados:", error));
});
