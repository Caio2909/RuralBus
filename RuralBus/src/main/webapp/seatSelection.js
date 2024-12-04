	// Assentos ocupados (simulação)
const occupiedSeats = [];

// Preencher o dropdown com assentos disponíveis
document.addEventListener("DOMContentLoaded", () => {
    const seatSelect = document.getElementById("seatNumber");
    for (let i = 1; i <= 24; i++) {
        if (!occupiedSeats.includes(i)) {
            const option = document.createElement("option");
            option.value = i;
            option.textContent = `Assento ${i}`;
            seatSelect.appendChild(option);
        }
    }
});
