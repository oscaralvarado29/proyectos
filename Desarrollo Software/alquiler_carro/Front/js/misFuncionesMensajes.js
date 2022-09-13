function informacionCliente() {

    $.ajax({
        //url: "http://10.0.1.5:8080/api/Client/all",
        url: "http://localhost:8080/api/Client/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {

            let $select = $("#select-client");
            $.each(respuesta, function (id, name) {
                $select.append('<option value=' + name.idClient + '>' + name.name + '</option>');
                console.log(" El id del cliente es" + name.idClient);
            });
        }

    })
}

function informacionVehiculo() {

    $.ajax({
        //url: "http://10.0.1.5:8080/api/Vehicle/all",
        url: "http://localhost:8080/api/Vehicle/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {

            let $select = $("#select-vehicle");
            $.each(respuesta, function (id, name) {
                $select.append('<option value=' + name.idVehicle + '>' + name.name + '</option>');

            });
        }

    })
}

function informacionReservaciones() {
    let idClient = +$("#select-client").val();
    let idVehicle = +$("#select-vehicle").val();
    console.log("los id son: " + idClient + " - " + idVehicle);
    if (idClient.length != 0 && idVehicle .length != 0) {
        document.getElementById("select-reservation").innerHTML= "";
        $.ajax({
            //url: "http://10.0.1.5:8080/api/Client/all",
            url: "http://localhost:8080/api/Reservation/reservation-clients-vehicle/"+idClient+"/"+idVehicle,
            type: "GET",
            datatype: "JSON",
            success: function (respuesta) {
                console.log(respuesta);
                let $select = $("#select-reservation");
                $.each(respuesta, function (id, reservationInfo) {
                    $select.append('<option value=' + reservationInfo.idReservation + '>' + formatDate(new Date(reservationInfo.startDate)) +" - "+ formatDate(new Date(reservationInfo.devolutionDate)) +'</option>');
                    console.log(reservationInfo.idReservation);
                });
            }

        })
    } else {
        alert("Debe existir un cliente y un vehículo");
    }
}

function listarMensajes() {
    console.log("se esta ejecutando")
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Message/all",
        url: "http://localhost:8080/api/Message/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            pintarMensajes(respuesta);
        }

    })

}

function pintarMensajes(respuesta) {

    let myTable = "<table>";
    myTable += "<th>Mensaje</th>";
    myTable += "<th>Vehiculo</th>";
    myTable += "<th>Cliente</th>";
    myTable += "<th>Calificacion</th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    myTable += "<th> </th>";
    "</tr>";
    for (i = 0; i < respuesta.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + respuesta[i].messageText + "</td>";
        myTable += "<td>" + respuesta[i].vehicle.name + "</td>";
        myTable += "<td>" + respuesta[i].client.name + "</td>";
        myTable += "<td>" + respuesta[i].score + "</td>";
        myTable += "<td> <button onclick='cargarDatosMensaje(" + respuesta[i].idMessage + ")'>Lanzar</button>";
        myTable += "<td> <button onclick=' actualizarInformacionMensaje(" + respuesta[i].idMessage + ")'>Actualizar</button>";
        myTable += "<td> <button onclick='borrarMensaje(" + respuesta[i].idMessage + ")'>Borrar</button>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#mostrarMensajes").html(myTable);
}

function cargarDatosMensaje(id) {
    $.ajax({
        dataType: 'json',
        //url: "http://10.0.1.5:8080/api/Message/"+id,
        url: "http://localhost:8080/api/Message/" + id,
        type: 'GET',

        success: function (response) {
            console.log(response);
            var item = response;

            $("#messagetext").val(item.messageText);
            $("#score").val(item.score);

        },
        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
}

function agregarMensaje() {
    if ($("#messagetext").val().length == 0 || $("#score").val().length == 0) {

        alert("Todos los campos son obligatorios");
    } else {
        let datos = {
            messageText: $("#messagetext").val(),
            vehicle: {
                idVehicle: +$("#select-vehicle").val()
            },
            client: {
                idClient: +$("#select-client").val()
            },
            reservation: {
                idReservation: +$("#select-reservation").val()
            },
            score: parseFloat( $("#score").val())
        };

        console.log(datos.reservation.idReservation);
        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify(datos),

            //url: "http://10.0.1.5:8080/api/Message/save",
            url: "http://localhost:8080/api/Message/save",


            success: function (response) {
                console.log(response);
                console.log("Se guardo correctamente");
                alert("Se guardo correctamente");
                window.location.reload()

            },

            error: function (jqXHR, textStatus, errorThrown) {
                alert("No se guardo correctamente");
            }
        });
    }
}

function actualizarInformacionMensaje(idElemento) {
    let myData = {
        idMessage: idElemento,
        messageText: $("#messagetext").val(),
        vehicle: {
            idVehicle: +$("#select-vehicle").val()
        },
        client: {
            idClient: +$("#select-client").val()
        },
        reservation: {
            idReservation: +$("#select-reservation").val()
        },
        score: parseFloat( $("#score").val())
        };
    console.log(myData);
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Message/update",
        url: "http://localhost:8080/api/Message/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#mostrarMensajes").empty();
            $("#messagetext").val("");
            $("#score").val("");
            listarMensajes();
            alert("se ha Actualizado correctamente el Mensaje")
        }
    });

}

function borrarMensaje(idElemento) {
    let myData = {
        idMessage: idElemento
    };
    let dataToSend = JSON.stringify(myData);
    console.log(dataToSend);
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Message/delete" ,
        url: "http://localhost:8080/api/Message/delete" ,
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#mostrarMensajes").empty();
            listarMensajes();
            alert("Se ha eliminado correctamente")
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se elimino el mensaje");
        }

    });

}

function  formatDate (date) {
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
    formatted_date = dayString +"-" + monthString +"-" +year;
    return formatted_date;
}