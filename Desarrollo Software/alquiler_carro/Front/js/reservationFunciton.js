function getReservation () {
  $.ajax({
    url: 'http://10.0.0.4:8080/api/Reservation/all',
    // url: 'http://localhost:8080/api/Reservation/all',
    type: 'GET',
    datatype: 'JSON',
    success: function (response) {
      drawReservations(response)
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function drawReservations (reservations) {
  let tableReservations = '<table>'
  tableReservations += '<tr>'
  tableReservations += '<th>Fecha Inicio</th>'
  tableReservations += '<th>Fecha Devolucion</th>'
  tableReservations += '<th>Estado</th>'
  tableReservations += '<th>Vehiculo</th>'
  tableReservations += '<th>Cliente</th>'
  tableReservations += '<th> </th>'
  tableReservations += '<th> </th>'
  tableReservations += '<th> </th>'
  tableReservations += '</tr>'

  for (const element of reservations) {
    tableReservations += '<tr>'
    tableReservations +=
      '<td>' + formatDate(new Date(element.startDate), 1) + '</td>'
    tableReservations +=
      '<td>' + formatDate(new Date(element.devolutionDate), 1) + '</td>'
    tableReservations += '<td>' + element.status + '</td>'
    tableReservations += '<td>' + element.vehicle.name + '</td>'
    tableReservations += '<td>' + element.client.name + '</td>'
    tableReservations +=
      '<td><button  onclick="launchDataReservation(' +
      element.idReservation +
      ')">Lanzar</button></td>'
    tableReservations +=
      '<td><button  onclick="putReservation(' +
      element.idReservation +
      ')">Actualizar</button></td>'
    tableReservations +=
      '<td><button  onclick="deleteReservation(' +
      element.idReservation +
      ')">Borrar</button></td>'
    tableReservations += '</tr>'
  }
  tableReservations += '</table>'
  $('#mostrarReservas').html(tableReservations)
}
function launchDataReservation (idReservaton) {
  $.ajax({
    dataType: 'json',
    url: 'http://10.0.0.4:8080/api/Reservation/' + idReservaton,
    // url: 'http://localhost:8080/api/Reservation/' + idReservaton,
    type: 'GET',

    success: function (reservation) {
      $('#startDate').val(formatDate(new Date(reservation.startDate), 2))
      $('#devolutionDate').val(
        formatDate(new Date(reservation.devolutionDate), 2)
      )
      $('#status').val(reservation.status)
    },

    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function postReservation () {
  if (
    $('#startDate').val() === '' ||
    $('#devolutionDate').val() === '' ||
    $('#status').val() === '' ||
    $('#select-vehicle').val() === '' ||
    $('#select-client').val() === ''
  ) {
    alert('Todos los campos son Obligatorios')
  } else {
    let reservationData = {
      startDate: $('#startDate').val(),
      devolutionDate: $('#devolutionDate').val(),
      status: $('#status').val(),
      vehicle: {
        idVehicle: +$('#select-vehicle').val()
      },
      client: {
        idClient: +$('#select-client').val()
      }
    }
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: 'http://10.0.0.4:8080/api/Reservation/save',
      // url: 'http://localhost:8080/api/Reservation/save',
      data: JSON.stringify(reservationData),
      datatype: 'json',

      success: function () {
        cleanFieldsReservation()
        alert('La reserva se ha guardado correctamente')
      },
      error: function (jqXHR, exception) {
        let msgError = generalFunctions(jqXHR, exception)
        alert(msgError)
      }
    })
  }
}

function putReservation (reservationId) {
  if (
    $('#startDate').val() === '' ||
    $('#devolutionDate').val() === '' ||
    $('#status').val() === '' ||
    $('#select-vehicle').val() === '' ||
    $('#select-client').val() === ''
  ) {
    alert('Todos los campos deben estar llenos')
  } else {
    let reservatonData = {
      idReservation: reservationId,
      startDate: $('#startDate').val(),
      devolutionDate: $('#devolutionDate').val(),
      status: $('#status').val(),
      vehicle: {
        idVehicle: +$('#select-vehicle').val()
      },
      client: {
        idClient: +$('#select-client').val()
      }
    }
    $.ajax({
      datatype: 'json',
      data: JSON.stringify(reservatonData),
      contentType: 'application/JSON',
      url: 'http://10.0.0.4:8080/api/Reservation/update',
      // url: 'http://localhost:8080/api/Reservation/update',
      type: 'PUT',

      success: function () {
        cleanFieldsReservation()
        getReservation()
        alert('La reserva se ha actualizado correctamente')
      },
      error: function (jqXHR, exception) {
        let msgError = generalFunctions(jqXHR, exception)
        alert(msgError)
      }
    })
  }
}

function deleteReservation (reservationId) {
  let reservationData = {
    idReservation: reservationId
  }
  $.ajax({
    dataType: 'json',
    data: JSON.stringify(reservationData),
    url: 'http://10.0.0.4:8080/api/Reservation/delete',
    // url: 'http://localhost:8080/api/Reservation/delete',
    type: 'DELETE',
    contentType: 'application/JSON',
    success: function () {
      cleanFieldsReservation()
      getReservation()
      alert('La reserva se ha eliminado correctamente')
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function cleanFieldsReservation () {
  $('#mostrarReservas').empty()
  $('#startDate').val('')
  $('#devolutionDate').val('')
  $('#status').val('')
}
