function postReservation() {

    if ($("#startDate").val() === "" || $("#devolutionDate").val() === "" || $("#status").val() === "" || $("#select-vehicle").val() === "" || $("#select-client").val() === "") {
        alert("Todos los campos son Obligatorios")
    } else {
        let reservationData = {
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
        $.ajax({
            type: "POST",
            contentType: "application/json",
            //url: "http://10.0.1.5:8080/api/Reservation/save",
            url: "http://localhost:8080/api/Reservation/save",
            data: JSON.stringify(reservationData),
            datatype: "json",

            success: function (response) {
                $("#resultado5").empty();
                $("#startDate").val("");
                $("#devolutionDate").val("");
                $("#status").val("");
                alert("Laa reserva hecha por "+response.client.name+ "al vehiculo"+response.vehicle.name+ "se ha guardado correctamente");
            },
            error: function (jqXHR, exception){
                let msgError=generalFunctions(jqXHR, exception)
                alert(msgError)
            }

        });
    }
}



function getReservations() {
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Reservation/all",
        url: "http://localhost:8080/api/Reservation/all",
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
    myTable += "<th>Fecha Inicio</th>";
    myTable += "<th>Fecha Devolucion</th>";
    myTable += "<th>Estado</th>";
    myTable += "<th>Vehiculo</th>";
    myTable += "<th>Cliente</th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    "</tr>";

    for (i = 0; i < response.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + formatDate(new Date(response[i].startDate),1) + "</td>";
        myTable += "<td>" + formatDate(new Date(response[i].devolutionDate),1) + "</td>";
        myTable += "<td>" + response[i].status + "</td>";
        myTable += "<td>" + response[i].vehicle.name + "</td>";
        myTable += "<td>" + response[i].client.name + "</td>";
        myTable += '<td><button  onclick="cargarDatosReservation(' + response[i].idReservation + ')">Lanzar</button></td>';
        myTable += '<td><button  onclick="actualizarReservation(' + response[i].idReservation + ')">Actualizar</button></td>';
        myTable += '<td><button  onclick="borrarReservation(' + response[i].idReservation + ')">Borrar</button></td>';
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#mostrarReservas").html(myTable);
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
        //url: "http://10.0.1.5:8080/api/Reservation/delete" ,
        url: "http://localhost:8080/api/Reservation/delete" ,
        type: 'DELETE',
        contentType: "application/JSON",
        success: function (response) {
            console.log(response);
            $("#mostrarReservas").empty();
            alert("se ha Eliminado Correctamente!")
            getReservations();

        },

        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se Elimino Correctamente!")
        }
    });
}

//Capturar informacion para Actualizar
function cargarDatosReservation(id) {
    $.ajax({
        dataType: 'json',
        //url: "http://10.0.1.5:8080/api/Reservation/" + id,
        url: "http://localhost:8080/api/Reservation/" + id,
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
            //url: "http://10.0.1.5:8080/api/Reservation/update",
            url: "http://localhost:8080/api/Reservation/update",
            type: "PUT",

            success: function (response) {
                console.log(response);
                alert("se ha Actualizado Correctamente!")
                getReservations();

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