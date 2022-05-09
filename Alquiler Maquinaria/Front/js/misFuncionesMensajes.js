function autoInicioRelacionCliente() {

    $.ajax({
        url:"http://168.138.142.130:8080/api/Client/all",
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

function autoInicioMachine() {

    $.ajax({
        url:"http://168.138.142.130:8080/api/Machine/all",
        //url: "http://localhost:8080/api/Machine/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {

            let $select = $("#select-machine");
            $.each(respuesta, function (id, name) {
                $select.append('<option value=' + name.id + '>' + name.name + '</option>');

            });
        }

    })
}


function autoInicioMensajes() {
    console.log("se esta ejecutando")
    $.ajax({
        url:"http://168.138.142.130:8080/api/Message/all",
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
    myTable += "<td>Maquina</td>";
    myTable += "<td>Cliente</td>";
    "</tr>";
    for (i = 0; i < respuesta.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + respuesta[i].messageText + "</td>";
        myTable += "<td>" + respuesta[i].machine.name + "</td>";
        myTable += "<td>" + respuesta[i].client.name + "</td>";
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
        url:"http://168.138.142.130:8080/api/Message/"+id,
        //url: "http://localhost:8080/api/Message/" + id,
        type: 'GET',

        success: function (response) {
            console.log(response);
            var item = response;

            $("#messagetext").val(item.messageText);
            $("#select-client").val(item.client.name);
            $("#select-machine").val(item.machine.name);

        },

        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
}

function guardarInformacionMensajes() {
    if ($("#messagetext").val().length == 0) {

        alert("Todos los campos son obligatorios");
    } else {


        let var2 = {

            messageText: $("#messagetext").val(),
            machine: {
                id: +$("#select-machine").val()
            },
            client: {
                idClient: +$("#select-client").val()
            },


        };

        console.log(var2);
        $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify(var2),

            url:"http://168.138.142.130:8080/api/Message/save",
            //url: "http://localhost:8080/api/Message/save",


            success: function (response) {
                console.log(response);
                console.log("Se guardo correctamente");
                alert("Se guardo correctamente");
                window.location.reload()

            },

            error: function (jqXHR, textStatus, errorThrown) {
                window.location.reload()
                alert("No se guardo correctamente");


            }
        });
    }
}

function actualizarInformacionMensaje(idElemento) {
    let myData = {
        idMessage: idElemento,
        messageText: $("#messagetext").val(),
    };
    console.log(myData);
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url:"http://168.138.142.130:8080/api/Message/update",
        //url: "http://localhost:8080/api/Message/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultado").empty();
            $("#messagetext").val("");

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
        url:"http://168.138.142.130:8080/api/Message/"+idElemento,
        //url: "http://localhost:8080/api/Message/" + idElemento,
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultado").empty();
            autoInicioMensajes();
            alert("Se ha Eliminado.")
        }
    });

}