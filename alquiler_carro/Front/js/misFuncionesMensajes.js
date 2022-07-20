function autoInicioRelacionCliente() {

    $.ajax({
        url: "http:// 52.205.54.183:8080/api/Client/all",
        //url: "http://localhost:8080/api/Client/all",
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

function autoInicioVehiculo() {

    $.ajax({
        url: "http:// 52.205.54.183:8080/api/Vehicle/all",
        //url: "http://localhost:8080/api/Vehicle/all",
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


function autoInicioMensajes() {
    console.log("se esta ejecutando")
    $.ajax({
        url: "http:// 52.205.54.183:8080/api/Message/all",
        //url: "http://localhost:8080/api/Message/all",
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
    myTable += "<td>Mensaje</td>";
    myTable += "<td>Vehiculo</td>";
    myTable += "<td>Cliente</td>";
    myTable += "<td>Calificacion</td>";
    "</tr>";
    for (i = 0; i < respuesta.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + respuesta[i].messageText + "</td>";
        myTable += "<td>" + respuesta[i].vehicle.name + "</td>";
        myTable += "<td>" + respuesta[i].client.name + "</td>";
        myTable += "<td>" + respuesta[i].score + "</td>";
        myTable += "<td> <button onclick=' actualizarInformacionMensaje(" + respuesta[i].idMessage + ")'>Actualizar</button>";
        myTable += "<td> <button onclick='cargarDatosMensaje(" + respuesta[i].idMessage + ")'>Lanzar</button>";
        myTable += "<td> <button onclick='borrarMensaje(" + respuesta[i].idMessage + ")'>Borrar</button>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#resultadoMensajes").html(myTable);
}

function cargarDatosMensaje(id) {
    $.ajax({
        dataType: 'json',
        url: "http:// 52.205.54.183:8080/api/Message/"+id,
        //url: "http://localhost:8080/api/Message/" + id,
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

function guardarInformacionMensajes() {
    if ($("#messagetext").val().length == 0 || $("#score").val().length == 0) {

        alert("Todos los campos son obligatorios");
    } else {
        let var2 = {
            messageText: $("#messagetext").val(),
            vehicle: {
                idVehicle: +$("#select-vehicle").val()
            },
            client: {
                idClient: +$("#select-client").val()
            },
            score: parseFloat( $("#score").val())
        };

        console.log(var2);
        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify(var2),

            url: "http:// 52.205.54.183:8080/api/Message/save",
            //url: "http://localhost:8080/api/Message/save",


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
        score:parseFloat( $("#score").val())
    };
    console.log(myData);
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "http:// 52.205.54.183:8080/api/Message/update",
        //url: "http://localhost:8080/api/Message/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultado").empty();
            $("#messagetext").val("");
            $("#score").val("");
            autoInicioMensajes();
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
        url: "http:// 52.205.54.183:8080/api/Message/delete" ,
        //url: "http://localhost:8080/api/Message/delete" ,
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultado").empty();
            autoInicioMensajes();
            alert("Se ha eliminado correctamente")
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("No se elimino el mensaje");
        }

    });

}