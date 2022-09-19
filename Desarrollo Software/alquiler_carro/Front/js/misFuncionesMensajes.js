function getDataReservations() {
    let idClient = +$("#select-client").val();
    let idVehicle = +$("#select-vehicle").val();
    if (idClient.length !== 0 && idVehicle .length !== 0) {
        document.getElementById("select-reservation").innerHTML= "";
        $.ajax({
            //url: "http://10.0.1.5:8080/api/Client/all",
            url: "http://localhost:8080/api/Reservation/reservation-clients-vehicle/"+idClient+"/"+idVehicle,
            type: "GET",
            datatype: "JSON",
            success: function (respuesta) {
                let $select = $("#select-reservation");
                $.each(respuesta, function (_id, reservationInfo) {
                    $select.append('<option value=' + reservationInfo.idReservation + '>' + formatDate(new Date(reservationInfo.startDate),1) +" - "+ formatDate(new Date(reservationInfo.devolutionDate),1) +'</option>');
                });
            },
            error: function (jqXHR, exception){
                let msgError=generalFunctions(jqXHR, exception)
                alert(msgError)
            }

        })
    } else {
        alert("Debe existir un cliente y un vehículo");
    }
}

function getMessages() {
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Message/all",
        url: "http://localhost:8080/api/Message/all",
        type: "GET",
        datatype: "JSON",
        success: function (messages) {
            drawMessage(messages);
        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }

    })

}

function drawMessage(messages) {

    let tableessages = "<table>";
    tableessages += "<th>Mensaje</th>";
    tableessages += "<th>Vehiculo</th>";
    tableessages += "<th>Cliente</th>";
    tableessages += "<th>Calificación</th>";
    tableessages += "<th> </th>";
    tableessages += "<th> </th>";
    tableessages += "<th> </th>";
    tableessages += "</tr>";
    for (const element of messages) {
        tableessages += "<tr>";
        tableessages += "<td>" + element.messageText + "</td>";
        tableessages += "<td>" + element.vehicle.name + "</td>";
        tableessages += "<td>" + element.client.name + "</td>";
        tableessages += "<td>" + element.score + "</td>";
        tableessages += "<td> <button onclick='launchDataMessage(" + element.idMessage + ")'>Lanzar</button>";
        tableessages += "<td> <button onclick=' putMessage(" + element.idMessage + ")'>Actualizar</button>";
        tableessages += "<td> <button onclick='deleteMessage(" + element.idMessage + ")'>Borrar</button>";
        tableessages += "</tr>";
    }
    tableessages += "</table>";
    $("#mostrarMensajes").html(tableessages);
}

function launchDataMessage(messageId) {
    $.ajax({
        dataType: 'json',
        //url: "http://10.0.1.5:8080/api/Message/"+messageId,
        url: "http://localhost:8080/api/Message/" + messageId,
        type: 'GET',

        success: function (message) {
            $("#messagetext").val(message.messageText);
            $("#score").val(message.score);

        },
        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }
    });
}

function postMessage() {
    if ($("#messagetext").val() === "" || $("#score").val() === "" || $("#select-client").val() === "" || $("#select-vehicle").val() === "" || $("#select-reservation").val() === "") {

        alert("Todos los campos son obligatorios");
    } else {
        let messageData = {
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

        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify(messageData),
            //url: "http://10.0.1.5:8080/api/Message/save",
            url: "http://localhost:8080/api/Message/save",

            success: function () {
                $("#mostrarMensajes").empty();
                $("#messagetext").val("");
                $("#score").val("");
                alert("El mensaje se guardo correctamente");

            },

            error: function (jqXHR, exception){
                let msgError=generalFunctions(jqXHR, exception)
                alert(msgError)
            }
        });
    }
}

function putMessage(idMessage) {
    if ($("#messagetext").val() === "" || $("#score").val() === "" || $("#select-client").val() === "" || $("#select-vehicle").val() === "" || $("#select-reservation").val() === "") {

        alert("Todos los campos son obligatorios");
    } else {
        let messageData = {
            idMessage: idMessage,
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
            score: parseFloat($("#score").val())
        };
        $.ajax({
            //url: "http://10.0.1.5:8080/api/Message/update",
            url: "http://localhost:8080/api/Message/update",
            type: "PUT",
            data: JSON.stringify(messageData),
            contentType: "application/JSON",
            datatype: "JSON",
            success: function () {
                $("#mostrarMensajes").empty();
                $("#messagetext").val("");
                $("#score").val("");
                getMessages();
                alert("se ha actualizado correctamente el mensaje")
            },
            error: function (jqXHR, exception){
                let msgError=generalFunctions(jqXHR, exception)
                alert(msgError)
            }
        });
    }
}

function deleteMessage(messageId) {
    let messageData = {
        idMessage: messageId
    };
    $.ajax({
        //url: "http://10.0.1.5:8080/api/Message/delete" ,
        url: "http://localhost:8080/api/Message/delete" ,
        type: "DELETE",
        data: JSON.stringify(messageData),
        contentType: "application/JSON",
        datatype: "JSON",
        success: function () {
            $("#mostrarMensajes").empty();
            $("#messagetext").val("");
            $("#score").val("");
            getMessages();
            alert("Se ha eliminado el mensaje correctamente")
        },

        error: function (jqXHR, exception){
            let msgError=generalFunctions(jqXHR, exception)
            alert(msgError)
        }

    });

}

