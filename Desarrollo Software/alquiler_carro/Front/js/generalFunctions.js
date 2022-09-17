function generalFunctions (jqXHR, exception) {
    let msg = '';
    if (jqXHR.status === 0) {
        msg += 'Not connect.\n Verify Network.';
    } else if (jqXHR.status === 404) {
        msg += 'Requested page not found. [404]';
    } else if (jqXHR.status === 500) {
        msg += 'Internal Server Error [500].';
    } else if (exception === 'parsererror') {
        msg += 'Requested JSON parse failed.';
    } else if (exception === 'timeout') {
        msg += 'Time out error.';
    } else if (exception === 'abort') {
        msg += 'Ajax request aborted.';
    } else {
        msg += 'Uncaught Error.\n' + jqXHR.responseText;
    }
    return(msg);
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