function getCategories () {
  $.ajax({
    url: 'http://10.0.0.4:8080/api/Category/all',
    // url: 'http://localhost:8080/api/Category/all',
    type: 'GET',
    datatype: 'JSON',
    success: function (response) {
      let $select = $('#select-category')
      $.each(response, function (_id, name) {
        $select.append(
          '<option value=' + name.idCategory + '>' + name.name + '</option>'
        )
      })
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function getVehicles () {
  $.ajax({
    url: 'http://10.0.0.4:8080/api/Vehicle/all',
    // url: 'http://localhost:8080/api/Vehicle/all',
    type: 'GET',
    datatype: 'JSON',
    success: function (vehicle) {
      drawVehicle(vehicle)
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function drawVehicle (vehicle) {
  let tableVehicle = '<table>'
  tableVehicle += '<tr>'
  tableVehicle += '<th>Tipo de vehiculo</th>'
  tableVehicle += '<th>Nombre</th>'
  tableVehicle += '<th>Color</th>'
  tableVehicle += '<th>Marca</th>'
  tableVehicle += '<th>Modelo</th>'
  tableVehicle += '<th>Caballos de fuerza</th>'
  tableVehicle += '<th>Cilindros del motor</th>'
  tableVehicle += '<th>Puestos del vehiculo</th>'
  tableVehicle += '<th>Mensajes</th>'
  tableVehicle += '<th> </th>'
  tableVehicle += '<th> </th>'
  tableVehicle += '<th> </th>'
  tableVehicle += '</tr>'

  for (const element of vehicle) {
    tableVehicle += '<tr>'
    tableVehicle += '<td>' + element.category.name + '</td>'
    tableVehicle += '<td>' + element.name + '</td>'
    tableVehicle += '<td>' + element.color + '</td>'
    tableVehicle += '<td>' + element.brand + '</td>'
    tableVehicle += '<td>' + element.model + '</td>'
    tableVehicle += '<td>' + element.horsePower + '</td>'
    tableVehicle += '<td>' + element.engineCylinders + '</td>'
    tableVehicle += '<td>' + element.seating + '</td>'
    if (typeof element.messages.messageText === 'undefined') {
      tableVehicle += '<td>' + 'Ninguno' + '</td>'
    } else {
      tableVehicle += '<td>' + element.messages.messageText + '</td>'
    }
    tableVehicle +=
      '<td><button onclick="launchDataVehicle(' +
      element.idVehicle +
      ')">Lanzar</button></td>'
    tableVehicle +=
      '<td><button onclick="putVehicle(' +
      element.idVehicle +
      ')">Actualizar</button></td>'
    tableVehicle +=
      '<td><button onclick="deleteVehicle(' +
      element.idVehicle +
      ')">Borrar</button></td>'
    tableVehicle += '</tr>'
  }
  tableVehicle += '</table>'
  $('#mostrarVehiculos').html(tableVehicle)
}

function launchDataVehicle (vehicleId) {
  $.ajax({
    dataType: 'json',
    url: 'http://10.0.0.4:8080/api/Vehicle/' + vehicleId,
    // url: 'http://localhost:8080/api/Vehicle/' + vehicleId,
    type: 'GET',

    success: function (response) {
      $('#name').val(response.name)
      $('#color').val(response.color)
      $('#brand').val(response.brand)
      $('#model').val(response.model)
      $('#horsePower').val(response.horsePower)
      $('#engineCylinders').val(response.engineCylinders)
      $('#seating').val(response.seating)
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function postVehicle () {
  if (
    $('#name').val() === '' ||
    $('#color').val() === '' ||
    $('#brand').val() === '' ||
    $('#model').val() === '' ||
    $('#horsePower').val() === '' ||
    $('#engineCylinders').val() === '' ||
    $('#seating').val() === '' ||
    $('#select-category').val() === ''
  ) {
    alert('Todos los campos son obligatorios')
  } else {
    let vehicleData = {
      name: $('#name').val(),
      color: $('#color').val(),
      brand: $('#brand').val(),
      model: $('#model').val(),
      horsePower: $('#horsePower').val(),
      engineCylinders: $('#engineCylinders').val(),
      seating: $('#seating').val(),
      category: {
        idCategory: +$('#select-category').val()
      }
    }
    $.ajax({
      type: 'POST',
      contentType: 'application/json',
      url: 'http://10.0.0.4:8080/api/Vehicle/save',
      // url: 'http://localhost:8080/api/Vehicle/save',
      data: JSON.stringify(vehicleData),
      datatype: 'json',
      success: function (response) {
        cleanFieldsVehicle()
        alert('El vehiculo ' + response.name + ' se ha guardado correctamente')
      },
      error: function (jqXHR, exception) {
        let msgError = generalFunctions(jqXHR, exception)
        alert(msgError)
      }
    })
  }
}

function putVehicle (vehicleId) {
  if (
    $('#name').val() === '' ||
    $('#color').val() === '' ||
    $('#brand').val() === '' ||
    $('#model').val() === '' ||
    $('#horsePower').val() === '' ||
    $('#engineCylinders').val() === '' ||
    $('#seating').val() === '' ||
    $('#select-category').val() === ''
  ) {
    alert('Todos los campos son obligatorios')
  } else {
    let vehicleData = {
      idVehicle: vehicleId,
      name: $('#name').val(),
      color: $('#color').val(),
      brand: $('#brand').val(),
      model: $('#model').val(),
      horsePower: $('#horsePower').val(),
      engineCylinders: $('#engineCylinders').val(),
      seating: $('#seating').val(),
      category: {
        idCategory: +$('#select-category').val()
      }
    }
    console.log(vehicleData)
    $.ajax({
      datatype: 'json',
      data: JSON.stringify(vehicleData),
      contentType: 'application/JSON',
      url: 'http://10.0.0.4:8080/api/Vehicle/update',
      // url: 'http://localhost:8080/api/Vehicle/update',
      type: 'PUT',

      success: function (response) {
        cleanFieldsVehicle()
        getVehicles()
        alert(
          'El vehiculo ' + response.name + ' se ha actualizado correctamente'
        )
      },
      error: function (jqXHR, exception) {
        let msgError = generalFunctions(jqXHR, exception)
        alert(msgError)
      }
    })
  }
}

function deleteVehicle (vehicleId) {
  let vehicleData = {
    idVehicle: vehicleId
  }
  $.ajax({
    dataType: 'json',
    data: JSON.stringify(vehicleData),
    url: 'http://10.0.0.4:8080/api/Vehicle/delete',
    // url: 'http://localhost:8080/api/Vehicle/delete',
    type: 'DELETE',
    contentType: 'application/JSON',
    success: function () {
      cleanFieldsVehicle()
      alert('El vehiculo se ha eliminado correctamente')
      getVehicles()
    },
    error: function (jqXHR, exception) {
      let msgError = generalFunctions(jqXHR, exception)
      alert(msgError)
    }
  })
}

function cleanFieldsVehicle () {
  $('#name').val('')
  $('#color').val('')
  $('#brand').val('')
  $('#model').val('')
  $('#horsePower').val('')
  $('#engineCylinders').val('')
  $('#seating').val('')
  $('#mostrarVehiculos').empty()
}
