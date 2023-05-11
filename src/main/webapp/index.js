const newHorarioForm = document.getElementById('horario-form')
const marcacoesForm = document.getElementById('marcacoes-form')

const base_url = "http://localhost:8080/pontos-servlet"


let horario_edit_id = null
let marca_edit_id = null


//EVENT LISTENERS
newHorarioForm.addEventListener('submit', function(event) {
  event.preventDefault();
  
  const entrada = document.getElementById('entradaMarcarHoras').value
  const saida = document.getElementById('saidaMarcarHoras').value

  if (!verificaTabela('horarios-table')) {
    addNewHorario(entrada, saida)
  } else {
    alert('Limite maximo de registros atingido')
  }
});

marcacoesForm.addEventListener('submit', function(event) {
  event.preventDefault();
  
  const entrada = document.getElementById('marcacoesEntradaInput').value
  const saida = document.getElementById('marcacoesSaidaInput').value

  addNewMarcacoes(entrada, saida)

});

//CORE FUNCTIONS
function addNewHorario(entrada, saida) {
  const table = document.getElementById('horarios-table');
  const rowCount = table.rows.length;
  const row = table.insertRow(rowCount); 
  const id = rowCount; 

  const idCell = row.insertCell(0)
  const entradaCell = row.insertCell(1); 
  const saidaCell = row.insertCell(2); 
  const editCell = row.insertCell(3); 
  const deleteCell = row.insertCell(4); 

  idCell.innerHTML = id;
  entradaCell.innerHTML = entrada; 
  saidaCell.innerHTML = saida; 
  editCell.innerHTML = `<button id='edit-horario-${id}' type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal-edit-horas" onclick="setHorarioEditId(this.parentNode.parentNode.rowIndex, this.id)">Edit</button>`; 
  deleteCell.innerHTML = '<button type="button" class="btn btn-danger btn-horario" onclick="deleteRegistro(this)">Delete</button>';
}

function addNewMarcacoes(entrada, saida) {
  const table = document.getElementById('marcacoes-table');
  const rowCount = table.rows.length;
  const row = table.insertRow(rowCount); 
  const id = rowCount; 

  const idCell = row.insertCell(0)
  const entradaCell = row.insertCell(1); 
  const saidaCell = row.insertCell(2); 
  const editCell = row.insertCell(3); 
  const deleteCell = row.insertCell(4); 

  idCell.innerHTML = id;
  entradaCell.innerHTML = entrada; 
  saidaCell.innerHTML = saida; 
  editCell.innerHTML = `<button id='edit-marca-${id}' type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal-edit-marcas" onclick="setHorarioEditId(this.parentNode.parentNode.rowIndex, this.id)">Edit</button>`; 
  deleteCell.innerHTML = '<button type="button" class="btn btn-danger" onclick="deleteRegistro(this)">Delete</button>';
}

async function setAtrasos() {
  const horarios = adicionarDatas(getHorarios())
  const marcas = adicionarDatas(getMarcas())
  const tabela = document.getElementById('atraso-tbody')

  const response = await fetch(base_url + '/CalculoHorario', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: new URLSearchParams({
      'horarios': horarios.toString(),
      'marcacoes': marcas.toString()
    })
  })

	var data = await response.text()
  tabela.innerHTML = ""

  let atrasos = converterStringParaArray(data)

  for (let i = 0; i < atrasos.length; i++) {
    const objeto = atrasos[i];

    if (objeto.tipo == 'Atraso') {
      const novaLinha = tabela.insertRow();

      const entradaCelula = novaLinha.insertCell();
      const saidaCelula = novaLinha.insertCell();
  
      entradaCelula.textContent = objeto.entrada;
      saidaCelula.textContent = objeto.saida;
      
    } else {
      continue
    }
  }

}

async function setHorasExtras() {
  const horarios = adicionarDatas(getHorarios())
  const marcas = adicionarDatas( getMarcas())

  const tabela = document.getElementById('horaExtra-tbody')


  const response = await fetch(base_url + '/CalculoHorario', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: new URLSearchParams({
      'horarios': horarios.toString(),
      'marcacoes': marcas.toString()
    })
  })

	var data = await response.text()
  tabela.innerHTML = ""

  let horasExtras = converterStringParaArray(data)


  for (let i = 0; i < horasExtras.length; i++) {
    const objeto = horasExtras[i];

    if (objeto.tipo == 'Hora-Extra') {
      const novaLinha = tabela.insertRow();

      const entradaCelula = novaLinha.insertCell();
      const saidaCelula = novaLinha.insertCell();
  
      entradaCelula.textContent = objeto.entrada;
      saidaCelula.textContent = objeto.saida;
      
    } else {
      continue
    }
  }

}

function getHorarios() {
  var tabelaHorarios = document.getElementById("horarios-table");
  var horarios = tabelaHorarios.getElementsByTagName("tr");
  var arrayHorarios = [];

  for (var i = 1; i < horarios.length; i++) {
    var celulas = horarios[i].getElementsByTagName("td");

    for (var j = 0; j < celulas.length; j++) {
      var valor = celulas[j].innerHTML;
      if (j === 1) {
        arrayHorarios.push(valor);
      } else if (j === 2) {
        arrayHorarios.push(valor);
      }
    }
  }

  return arrayHorarios
}

function getMarcas() {
  var tabelaMarcas = document.getElementById("marcacoes-table");
  var marcas = tabelaMarcas.getElementsByTagName("tr");
  var arrayMarcas = [];

  for (var i = 1; i < marcas.length; i++) {
    var celulas = marcas[i].getElementsByTagName("td");

    for (var j = 0; j < celulas.length; j++) {
      var valor = celulas[j].innerHTML;

      if (j === 1) {
        arrayMarcas.push(valor);
      } else if (j === 2) {
        arrayMarcas.push(valor);
      }
    }
  }
  
  return arrayMarcas
}

//UTILS
function adicionarDatas(arrayHorarios) {
  const formatoData = 'dd/MM/yyyy';
  const horariosComDatas = [];
  let compareArray = []

  let dataAnterior = new Date();
  
  for (let i = 0; i < arrayHorarios.length; i++) {
    const horario = arrayHorarios[i];

    const [hora, minuto] = horario.split(':');
    const data = new Date(dataAnterior.getTime()); 

    data.setHours(Number(hora));
    data.setMinutes(Number(minuto));

    if (i > 0 && data.getHours() < arrayHorarios[i - 1].split(':')[0]) {
      data.setDate(data.getDate() + 1);
    }

    const dataFormatada = formatDate(data, formatoData);

    const horarioComData = `${dataFormatada} ${horario}`;
    horariosComDatas.push(horarioComData);

    dataAnterior = data;
  }

  return horariosComDatas;
}

function formatDate(date, format) {
  const options = {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
  };

  return date.toLocaleDateString(undefined, options);
}

function converterStringParaArray(inputString) {
  const trimmedString = inputString.replace(/\[|\]/g, '');
  
  const substrings = trimmedString.split(',');

  const objectArray = [];

  for (let i = 0; i < substrings.length; i += 3) {
    const tipo = substrings[i].split('=')[1];
    const entradaWithDates = substrings[i + 1].split('=')[1];
    const saidaWithDates = substrings[i + 2].split('=')[1];

    const entradaWithoutDates = entradaWithDates.split(" ")
    const saidaWithoutDates = saidaWithDates.split(" ")

    const objeto = {
      tipo: tipo.trim(),
      entrada: entradaWithoutDates[1].trim(),
      saida: saidaWithoutDates[1].trim()
    };

    objectArray.push(objeto);
  }

  return objectArray;
}

function deleteRegistro (botao) {
  const celulaAcao = botao.parentNode;
  const linha = celulaAcao.parentNode;
  const tabela = linha.parentNode;

  tabela.deleteRow(linha.rowIndex);
}

function editarColuna(form, event, targetTable) {
  event.preventDefault();

  const tabela = document.getElementById(targetTable);
  const linhas = tabela.rows;
  const entrada = form.elements['entrada'].value;
  const saida = form.elements['saida'].value;

  if (tabela.id === 'horarios-table') {
    const celulaEntrada = linhas[horario_edit_id].cells[1];
    const celulaSaida = linhas[horario_edit_id].cells[2];
    
    celulaEntrada.textContent = entrada;
    celulaSaida.textContent = saida;
  } else {
    const celulaEntrada = linhas[marca_edit_id].cells[1];
    const celulaSaida = linhas[marca_edit_id].cells[2];
    
    celulaEntrada.textContent = entrada;
    celulaSaida.textContent = saida;
  }
}

function setHorarioEditId(index, edit_id) {

  if (edit_id === `edit-horario-${index}`) {
    horario_edit_id = index
  } else {
    marca_edit_id = index
  }

}

function verificaTabela(nomeTabela) {
  const tabela = document.getElementById(nomeTabela);

  if (!tabela) {
    return false;
  }

  const registros = tabela.querySelectorAll('tr');

  const temTresRegistros = registros.length === 4;
  
  return temTresRegistros;
}