function getReportDate () {
  let fechaInicio = $('#rStartDate').val()
  let fechaCierre = $('#rDevolutionDate').val()

  $.ajax({
    url:
      'http://10.0.0.4:8080/api/Reservation/report-dates/' +
      fechaInicio +
      '/' +
      fechaCierre,
    //url:
    //  'http://localhost:8080/api/Reservation/report-dates/' + fechaInicio + '/' + fechaCierre,
    type: 'GET',
    datatype: 'JSON',
    success: function (reportDate) {
      drawReportDates(reportDate)
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function drawReportDates (reportDate) {
  let tableReportDate = '<table>'
  tableReportDate += '<tr>'
  tableReportDate += '<th>Fecha Inicio</th>'
  tableReportDate += '<th>Fecha Devolucion</th>'
  tableReportDate += '<th>Estado</th>'
  tableReportDate += '<th>Cliente</th>'
  tableReportDate += '<th>Vehiculo</th>'
  tableReportDate += '<th>Mensaje</th>'
  tableReportDate += '<th>Califcación</th>'
  tableReportDate += '</tr>'

  for (const element of reportDate) {
    tableReportDate += '<tr>'
    tableReportDate +=
      '<td>' + formatDate(new Date(element.startDate), 1) + '</td>'
    tableReportDate +=
      '<td>' + formatDate(new Date(element.devolutionDate), 1) + '</td>'
    tableReportDate += '<td>' + element.status + '</td>'
    tableReportDate += '<td>' + element.client.name + '</td>'
    tableReportDate += '<td>' + element.vehicle.name + '</td>'
    if (element.messages.length !== 0) {
      tableReportDate += '<td>' + element.messages[0].messageText + '</td>'
      tableReportDate += '<td>' + element.messages[0].score + '</td>'
    } else {
      tableReportDate += '<td>' + 'No hay mensajes' + '</td>'
      tableReportDate += '<td>' + 'No hay calificación' + '</td>'
    }
    tableReportDate += '</tr>'
  }
  tableReportDate += '</table>'
  $('#resultadoDate').html(tableReportDate)
}

function getReportStatus () {
  $.ajax({
    url: 'http://10.0.0.4:8080/api/Reservation/report-status',
    // url: 'http://localhost:8080/api/Reservation/report-status',
    type: 'GET',
    datatype: 'JSON',
    success: function (reportStatus) {
      drawReportStatus(reportStatus)
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function drawReportStatus (reportStatus) {
  let tableReportStatus = '<table>'
  tableReportStatus += '<tr>'
  tableReportStatus += '<th>completadas</th>'
  tableReportStatus += '<td>' + reportStatus.completed + '</td>'
  tableReportStatus += '<th>canceladas</th>'
  tableReportStatus += '<td>' + reportStatus.cancelled + '</td>'
  tableReportStatus += '</tr>'
  tableReportStatus += '</table>'
  $('#resultadoStatus').html(tableReportStatus)
}

function getReportClients () {
  $.ajax({
    url: 'http://10.0.0.4:8080/api/Reservation/report-clients',
    // url: 'http://localhost:8080/api/Reservation/report-clients',
    type: 'GET',
    datatype: 'JSON',
    success: function (reportClients) {
      drawReportClients(reportClients)
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function drawReportClients (reportClients) {
  let tableReportClients = '<table>'
  tableReportClients += '<th>Cantdad de reservas</th>'
  tableReportClients += '<th>Cliente</th>'
  tableReportClients += '<tr>'

  for (const element of reportClients) {
    tableReportClients += '<td>' + element.total + '</td>'
    tableReportClients += '<td>' + element.client.name + '</td>'
    tableReportClients += '</tr>'
  }
  tableReportClients += '</table>'
  $('#resultadoClientes').html(tableReportClients)
}
