// const horasTrabalho = ["08:00", "12:00", "13:30", "17:30"]

// const marcacoes = ["06:00", "20:00"]

function subtrairHorarios(horariosTrabalho, horariosMarcacoes) {
  const horarios = [...horariosTrabalho, ...horariosMarcacoes].sort();
  const resultados = [];

  for (let i = 0; i < horarios.length; i += 2) {
    const entrada = horarios[i];
    const saida = horarios[i + 1];
    let tipo = "";

    if (horariosTrabalho.includes(entrada) && horariosTrabalho.includes(saida) 
			&& horariosMarcacoes.includes(entrada) && horariosMarcacoes.includes(saida)) {
      continue;
    } else {
      let horarioEncontrado = false;
      for (var j = 0; j < horariosTrabalho.length; j += 2) {
        const entradaHorario = horariosTrabalho[j]
        const saidaHorario = horariosTrabalho[j+1]

        if (entrada >= entradaHorario && saida <= saidaHorario) {
          horarioEncontrado = true;
          break;
        }
      }

      if (horarioEncontrado) {
        tipo = "Atraso";
      } else {
        tipo = "Hora-Extra";
      }

      resultados.push({tipo, entrada, saida});
    }
  }

  return resultados;
}

function converterStringParaArray(string, campo) {
  string = string.replace(/\s+/g, '');

  const regex = new RegExp(`${campo}\\[id=(\\d+),entrada=(\\d{2}:\\d{2}),saida=(\\d{2}:\\d{2})\\]`, 'g');

  const horarios = [];

  let match;
  while ((match = regex.exec(string)) !== null) {
    const id = parseInt(match[1]);
    const entrada = match[2];
    const saida = match[3];

    const horario = {
      id: id,
      [campo]: {
        entrada: entrada,
        saida: saida,
      },
    };

    horarios.push(horario);
  }

  return horarios;
}

async function getHorariosDeTrabalho () {
	const base_url = "http://localhost:8080/pontos-servlet"

	const tableBody = document.getElementById("horario-trabalho-tbody")
	const response = await fetch(base_url + '/HorarioDeTrabalhoCreateAndFind')
	var data = await response.text()

	let horarios = converterStringParaArray(data, 'HorarioDeTrabalho')

	tableBody.innerHTML = ""


	horarios.map(horario => {
		tableBody.innerHTML += 
		`<tr>
			<td>${horario.id}</td>
			<td>${horario.HorarioDeTrabalho.entrada}</td>
			<td>${horario.HorarioDeTrabalho.saida}</td>
			<td>
				<button onclick="TEMP()" type="button" class="btn btn-primary">Edit</button>
			</td>
			<td>
				<button onclick="TEMP()" type="button" class="btn btn-danger">Delete</button>
			</td>
		</tr>
		`
	})

	horarios = []

}

function createNewHorarioTrabalho () {
	const base_url = "http://localhost:8080/pontos-servlet"

	$("#button-modal-horario-trabalho").click(function () {
		let entrada = $("#entradaMarcarHoras").val()
		let saida = $("#saidaMarcarHoras").val()
		
		fetch(base_url + '/HorarioDeTrabalhoCreateAndFind', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body: new URLSearchParams({
				'entradaMarcarHoras': entrada,
				'saidaMarcarHoras': saida
			})
		})
		getHorariosDeTrabalho()
		alert('Criado com sucesso')
	})	
}

async function getMarcacoesFeitas() {
	const base_url = "http://localhost:8080/pontos-servlet"

	const tableBody = document.getElementById("marcacoes-feitas-tbody")
	const response = await fetch(base_url + '/MarcacaoFeitaCreateAndFind')
	var data = await response.text()

	let marcacoes = converterStringParaArray(data, 'MarcacaoFeita')

	tableBody.innerHTML = ""

	marcacoes.map(marcacao => {
		tableBody.innerHTML += 
		`<tr>
			<td>${marcacao.id}</td>
			<td>${marcacao.MarcacaoFeita.entrada}</td>
			<td>${marcacao.MarcacaoFeita.saida}</td>
			<td>
				<button onclick="TEMP()" type="button" class="btn btn-primary">Edit</button>
			</td>
			<td>
				<button onclick="TEMP()" type="button" class="btn btn-danger">Delete</button>
			</td>
		</tr>
		`
	})
	marcacoes = []
}

function createNewMarcacao() {
	const base_url = "http://localhost:8080/pontos-servlet"

	$("#button-modal-marcacoes-feitas").click(function () {
		let entrada = $("#marcacoesEntradaInput").val()
		let saida = $("#marcacoesSaidaInput").val()
		
		fetch(base_url + '/MarcacaoFeitaCreateAndFind', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body: new URLSearchParams({
				'marcacoesEntradaInput': entrada,
				'marcacoesSaidaInput': saida
			})
		})
		alert('Criado com sucesso')
		location.reload()
	})	
}

function setAtrasoTable() {
	$("#calcular-atraso-btn").click(async function () {
		const base_url = "http://localhost:8080/pontos-servlet"

		const tableBody = document.getElementById("atraso-tbody")
		
		const responseMarks = await fetch(base_url + '/MarcacaoFeitaCreateAndFind')
		var dataMarks = await responseMarks.text()

		let marcacoes = converterStringParaArray(dataMarks, 'MarcacaoFeita')

		const responseHorarios = await fetch(base_url + '/HorarioDeTrabalhoCreateAndFind')
		var dataHorarios = await responseHorarios.text()

		let horarios = converterStringParaArray(dataHorarios, 'HorarioDeTrabalho')

		var horariosArray = []

		var marcacaoArray = []


		marcacoes.forEach(item => {
			marcacaoArray.push(item.MarcacaoFeita.entrada)
			marcacaoArray.push(item.MarcacaoFeita.saida)
		})

		horarios.forEach(item => {
			horariosArray.push(item.HorarioDeTrabalho.entrada)
			horariosArray.push(item.HorarioDeTrabalho.saida)
		})

		const atrasos = subtrairHorarios(horariosArray, marcacaoArray)

		tableBody.innerHTML = ''

		atrasos.map(item => {
			if (item.tipo === 'Atraso') {
				tableBody.innerHTML += 
				`<tr>
					<td>${item.entrada}</td>
					<td>${item.saida}</td>
				</tr>
				`
			}
		})
	})
}

function setHoraExtraTable() {
	$("#calcular-hora-extra-btn").click(async function () {
		const base_url = "http://localhost:8080/pontos-servlet"

		const tableBody = document.getElementById("horaExtra-tbody")
		
		const responseMarks = await fetch(base_url + '/MarcacaoFeitaCreateAndFind')
		var dataMarks = await responseMarks.text()

		let marcacoes = converterStringParaArray(dataMarks, 'MarcacaoFeita')

		const responseHorarios = await fetch(base_url + '/HorarioDeTrabalhoCreateAndFind')
		var dataHorarios = await responseHorarios.text()

		let horarios = converterStringParaArray(dataHorarios, 'HorarioDeTrabalho')

		var horariosArray = []

		var marcacaoArray = []


		marcacoes.forEach(item => {
			marcacaoArray.push(item.MarcacaoFeita.entrada)
			marcacaoArray.push(item.MarcacaoFeita.saida)
		})

		horarios.forEach(item => {
			horariosArray.push(item.HorarioDeTrabalho.entrada)
			horariosArray.push(item.HorarioDeTrabalho.saida)
		})

		const horasExtra = subtrairHorarios(horariosArray, marcacaoArray)

		tableBody.innerHTML = ''

		horasExtra.map(item => {
			if (item.tipo === 'Hora-Extra') {
				tableBody.innerHTML += 
				`<tr>
					<td>${item.entrada}</td>
					<td>${item.saida}</td>
				</tr>
				`
			}
		})
	})
}

