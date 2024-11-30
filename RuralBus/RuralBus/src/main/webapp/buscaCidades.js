
document.addEventListener("DOMContentLoaded", function() {
    const originInput = document.getElementById("origin");
    const originDropdown = document.getElementById("originDropdown");

    const destinationInput = document.getElementById("destination");
    const destinationDropdown = document.getElementById("destinationDropdown");


	// Função para remover acentos de uma string
	function removeAcentos(str) {
	    return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
	};
	async function fetchCities() {
	    try {		
	        const response = await fetch("https://servicodados.ibge.gov.br/api/v1/localidades/municipios");
	        const data = await response.json();
	        // Mapeia cidades para incluir o nome da cidade e a sigla do estado
	        return data.map(city => ({
	            nome: city.nome,
	            estadoSigla: city.microrregiao.mesorregiao.UF.sigla // Sigla do estado
	        }));
	    } catch (error) {
	        console.error("Erro ao buscar cidades:", error);
	        return [];
	    }
	}


	// Inicializar cidades
	let cities = [];
	fetchCities().then(data => {
	    cities = data;
	});

	// Função de autocomplete para campos de texto
	function setupAutocomplete(input, dropdown) {
	    input.addEventListener("input", () => {
	        const query = removeAcentos(input.value.toLowerCase()); // Remover acentos da busca
	        dropdown.innerHTML = ""; // Limpa o dropdown

	        if (query) {
	            // Filtra as cidades e limita a 5
	            const filteredCities = cities
	                .filter(city => removeAcentos(city.nome.toLowerCase()).includes(query)) // Remove acentos antes da comparação
	                .slice(0, 5); // Limita a 5 cidades

	            if (filteredCities.length) {
	                dropdown.style.display = "block";
	                filteredCities.forEach(city => {
	                    const listItem = document.createElement("li");
	                    listItem.textContent = `${city.nome} - ${city.estadoSigla}`; // Exibe cidade e sigla do estado
	                    listItem.addEventListener("click", () => {
	                        input.value = `${city.nome} - ${city.estadoSigla}`; // Preenche o input com cidade e sigla do estado
	                        dropdown.innerHTML = "";
	                        dropdown.style.display = "none";
	                    });
	                    dropdown.appendChild(listItem);
	                });
	            } else {
	                dropdown.style.display = "none";
	            }
	        } else {
	            dropdown.style.display = "none";
	        }
	    });

	    // Esconde o dropdown ao clicar fora
	    document.addEventListener("click", (e) => {
	        if (!input.contains(e.target) && !dropdown.contains(e.target)) {
	            dropdown.style.display = "none";
	        }
	    });
	}

	// Configurar autocomplete para os campos
	setupAutocomplete(originInput, originDropdown);
	setupAutocomplete(destinationInput, destinationDropdown);
});
	