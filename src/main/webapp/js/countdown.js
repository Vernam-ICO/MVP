    let selection = document.querySelector(".deal-end-date") !== null;
    // set the date we're counting down to
    if (selection){
        let deal_end_date = document.querySelector(".deal-end-date").textContent;
        console.log(deal_end_date);
        let target_date = new Date(deal_end_date).getTime();
        // letiables for time units
        let days, hours, minutes, seconds;

        // get tag element
        let countdown = document.getElementById('deal-countdown');

        // update the tag with id "countdown" every 1 second
        setInterval(function() {

            // find the amount of "seconds" between now and target
            let current_date = new Date().getTime();
            let seconds_left = (target_date - current_date) / 1000;

            // do some time calculations
            days = parseInt(seconds_left / 86400);
            seconds_left = seconds_left % 86400;

            hours = parseInt(seconds_left / 3600);
            seconds_left = seconds_left % 3600;

            minutes = parseInt(seconds_left / 60);
            seconds = parseInt(seconds_left % 60);

            // format countdown string + set tag value
            countdown.innerHTML = '<span data-value="' + days + '" class="days"><span class="value">' + days + '</span><b>Days</b></span><span class="hours"><span class="value">' + hours + '</span><b>Hours</b></span><span class="minutes"><span class="value">' + minutes + '</span><b>Mins</b></span><span class="seconds"><span class="value">' + seconds + '</span><b>Secs</b></span>';

        }, 1000);
    }

