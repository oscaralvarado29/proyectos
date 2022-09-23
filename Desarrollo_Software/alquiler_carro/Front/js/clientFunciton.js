function getClient () {
  $.ajax({
    url: 'http://191.234.206.72:8080/api/Client/all',
    // url: 'http://localhost:8080/api/Client/all',
    type: 'GET',
    datatype: 'JSON',
    success: function (clients) {
      drawInfoClients(clients)
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function drawInfoClients (clients) {
  let tableClients = '<table>'
  tableClients += '<th>Nombre</th>'
  tableClients += '<th>Email</th>'
  tableClients += '<th>Edad</th>'
  tableClients += '<th>Mensajes</th>'
  tableClients += '<th> </th>'
  tableClients += '<th> </th>'
  tableClients += '<th> </th>'
  tableClients += '</tr>'
  for (const element of clients) {
    tableClients += '<tr>'
    tableClients += '<td>' + element.name + '</td>'
    tableClients += '<td>' + element.email + '</td>'
    tableClients += '<td>' + element.age + '</td>'
    if (typeof element.messages.messageText === 'undefined') {
      tableClients += '<td>' + 'Ninguno' + '</td>'
    } else {
      tableClients += '<td>' + element.messages.messageText + '</td>'
    }
    tableClients +=
      '<td><button  onclick="launchDataClient(' +
      element.idClient +
      ')">Lanzar</button></td>'
    tableClients +=
      "<td> <button onclick=' putClient(" +
      element.idClient +
      ")'>Actualizar</button>"
    tableClients +=
      "<td> <button onclick='deleteClient(" +
      element.idClient +
      ")'>Borrar</button>"
    tableClients += '</tr>'
  }
  tableClients += '</table>'
  $('#mostrarClientes').html(tableClients)
}

function launchDataClient (clientId) {
  $.ajax({
    dataType: 'json',
    url: 'http://191.234.206.72:8080/api/Client/' + clientId,
    // url: 'http://localhost:8080/api/Client/' + clientId,
    type: 'GET',

    success: function (response) {
      $('#name').val(response.name)
      $('#email').val(response.email)
      $('#age').val(response.age)
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function postClient () {
  if (
    $('#email').val() === '' ||
    $('#name').val() === '' ||
    $('#age').val() === ''
  ) {
    alert('Todos los campos son obligatorios')
  } else {
    let dataClient = {
      email: $('#email').val(),
      name: $('#name').val(),
      age: $('#age').val()
    }

    $.ajax({
      type: 'POST',
      contentType: 'application/json; charset=utf-8',
      dataType: 'JSON',
      data: JSON.stringify(dataClient),
      url: 'http://191.234.206.72:8080/api/Client/save',
      // url: 'http://localhost:8080/api/Client/save',
      success: function (response) {
        cleanFieldsClient()
        alert('El cliente ' + response.name + ' se guardo correctamente')
      },

      error: function (jqXHR, exception) {
        let msgError = generalFunctions(jqXHR, exception)
        alert(msgError)
      }
    })
  }
}

function putClient (idElemento) {
  if (
    $('#email').val() === '' ||
    $('#name').val() === '' ||
    $('#Clage').val() === ''
  ) {
    alert('Todos los campos son obligatorios')
  } else {
    let clientData = {
      idClient: idElemento,
      email: $('#email').val(),
      name: $('#name').val(),
      age: $('#age').val()
    }
    $.ajax({
      url: 'http://191.234.206.72:8080/api/Client/update',
      // url: 'http://localhost:8080/api/Client/update',
      type: 'PUT',
      data: JSON.stringify(clientData),
      contentType: 'application/JSON',
      datatype: 'JSON',
      success: function (respuesta) {
        cleanFieldsClient()
        getClient()
        alert('Se ha actualizado correctamente el cliente ' + respuesta.name)
      },
      error: function (jqXHR, exception) {
        let msgError = generalFunctions(jqXHR, exception)
        alert(msgError)
      }
    })
  }
}

function deleteClient (idElemento) {
  let dataClient = {
    idClient: idElemento
  }
  $.ajax({
    url: 'http://191.234.206.72:8080/api/Client/delete',
    // url: 'http://localhost:8080/api/Client/delete',
    type: 'DELETE',
    data: JSON.stringify(dataClient),
    contentType: 'application/JSON',
    datatype: 'JSON',
    success: function () {
      cleanFieldsClient()
      getClient()
      alert('El cliente se ha eliminado correctamente')
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function cleanFieldsClient () {
  $('#email').val('')
  $('#name').val('')
  $('#age').val('')
  $('#mostrarClientes').empty()
}
