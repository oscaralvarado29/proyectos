function autoInicioRelacionCliente() {

    $.ajax({
        url: "http://52.205.54.183:8080/api/Client/all",
        //url:"http://localhost:8080/api/Client/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {

            let $select = $("#select-client");
            $.each(respuesta, function (id, name) {
                $select.append('<option value=' + name.idClient + '>' + name.name + '</option>');

            });
        }

    })
}

function autoInicioVehicule() {

    $.ajax({
        url: "http://52.205.54.183:8080/api/Vehicle/all",
        //url:"http://localhost:8080/api/Vehicle/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            let $select = $("#select-vehicle");
            $.each(respuesta, function (id, name) {
                $select.append('<option value=' + name.idVehicle + '>' + name.name + '</option>');
                console.log("select " + name.idVehicle);
            });
        }

    })
}

//Manejador "POST"
function agregarReservation() {

    if ($("#startDate").val().length == 0 || $("#devolutionDate").val().length == 0 || $("#status").val().length == 0) {
        alert("Todos los campos son Obligatorios")
    } else {
        let elemento = {
            startDate: $("#startDate").val(),
            devolutionDate: $("#devolutionDate").val(),
            status: $("#status").val(),
            vehicle: {
                idVehicle: +$("#select-vehicle").val()
            },
            client: {
                idClient: +$("#select-client").val()
            },

        }

        let dataToSend = JSON.stringify(elemento);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://52.205.54.183:8080/api/Reservation/save",
            //url: "http://localhost:8080/api/Reservation/save",
            data: dataToSend,
            datatype: "json",

            success: function (response) {
                console.log(response);
                //Limpiar Campos
                $("#resultado5").empty();
                $("#startDate").val("");
                $("#devolutionDate").val("");
                $("#status").val("");
                alert("Se ha guardado Correctamente!")
                window.location.reload()
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("No se guardo Correctamente!")
            }
        });
    }
}



function listarReservation() {
    $.ajax({
        url: "http://52.205.54.183:8080/api/Reservation/all",
        //url: "http://localhost:8080/api/Reservation/all",
        type: "GET",
        datatype: "JSON",
        success: function (response) {
            console.log(response);
            pintarRespuestaReservation(response);
        }
    });
}

function pintarRespuestaReservation(response) {

    let myTable = "<table>";
    myTable += "<tr>";
    myTable += "<td>Fecha Inicio</td>";
    myTable += "<td>Fecha Devolucion</td>";
    myTable += "<td>Estado</td>";
    myTable += "<td>Vehiculo</td>";
    myTable += "<td>Cliente</td>";
    "</tr>";

    for (i = 0; i < response.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + formatDate(new Date(response[i].startDate),1) + "</td>";
        myTable += "<td>" + formatDate(new Date(response[i].devolutionDate),1) + "</td>";
        myTable += "<td>" + response[i].status + "</td>";
        myTable += "<td>" + response[i].vehicle.name + "</td>";
        myTable += "<td>" + response[i].client.name + "</td>";
        myTable += '<td><button  onclick="borrarReservation(' + response[i].idReservation + ')">Borrar</button></td>';
        myTable += '<td><button  onclick="cargarDatosReservation(' + response[i].idReservation + ')">Lanzar</button></td>';
        myTable += '<td><button  onclick="actualizarReservation(' + response[i].idReservation + ')">Actualizar</button></td>';
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#miListaReservation").html(myTable);
}


//Manejador DELETE
function borrarReservation(idElemento) {
    let elemento = {
        idReservation: idElemento
    }

    let dataToSend = JSON.stringify(elemento);

    $.ajax({
        dataType: 'json',
        data: dataToSend,
        url: "http://52.205.54.183:8080/api/Reservation/delete" ,
        //url: "http://localhost:8080/api/Reservation/delete" ,
        type: 'DELETE',
        contentType: "application/JSON",
        success: function (response) {
            console.log(response);
            $("#miListaReservation").empty();
            alert("se ha Eliminado Correctamente!")
            listarReservation();

        },

        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se Elimino Correctamente!")
        }
    });
}
function  formatDate (date,version) {
    let day = date.getDate() + 1;
    let month = (date.getMonth() + 1);
    let year = date.getFullYear();
    let formatted_date;
    let dayString;
    let monthString;
    if (day <10){
        dayString = "0" + day.toString()
    }else{
        dayString = day.toString()
    }
    if (month <10){
        monthString = "0" + month.toString()
    }else{
        monthString = month.toString()
    }

    if (version == 1) {
        formatted_date = dayString +"-" + monthString +"-" +year;
    }    else {
        formatted_date = year +"-" + monthString +"-" + dayString;

    }
    return formatted_date;
}
//Capturar informacion para Actualizar
function cargarDatosReservation(id) {
    $.ajax({
        dataType: 'json',
        url: "http://52.205.54.183:8080/api/Reservation/" + id,
        //url: "http://localhost:8080/api/Reservation/" + id,
        type: 'GET',

        success: function (response) {
            console.log(response);
            var item = response;

            $("#startDate").val(formatDate(new Date(item.startDate),2));
            $("#devolutionDate").val(formatDate(new Date(item.devolutionDate),2));
            $("#status").val(item.status);

        },

        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
}

//Manejador PUT
function actualizarReservation(idElemento) {

    if ($("#startDate").val().length == 0 || $("#devolutionDate").val().length == 0 || $("#status").val().length == 0) {
        alert("Todos los campos deben estar llenos")
    } else {
        let elemento = {
            idReservation: idElemento,
            startDate: $("#startDate").val(),
            devolutionDate: $("#devolutionDate").val(),
            status: $("#status").val(),
            vehicle: {
                idVehicle: +$("#select-vehicle").val()
            },
            client: {
                idClient: +$("#select-client").val()
            },
        }

        let dataToSend = JSON.stringify(elemento);

        $.ajax({
            datatype: 'json',
            data: dataToSend,
            contentType: "application/JSON",
            url: "http://52.205.54.183:8080/api/Reservation/update",
            //url: "http://localhost:8080/api/Reservation/update",
            type: "PUT",

            success: function (response) {
                console.log(response);
                alert("se ha Actualizado Correctamente!")
                listarReservation();

                //Limpiar Campos

                $("#startDate").val("");
                $("#devolutionDate").val("");
                $("#status").val("");

            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("No se Actualizo Correctamente!")
            }
        });
    }
}