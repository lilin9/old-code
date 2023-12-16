$(function () {
    var hiden = {
        'transform': 'rotate(0deg)',
        'opacity': '0',
        'transition': 'all 0.5s',
    }
    var show = {
        'transform': 'rotate(360deg)',
        'opacity': '1',
        'transition': 'all 0.5s',
    }
    $(".tab-but-two").click(function () {
        $(".form-one").css(hiden);
        setTimeout(function () {
            $(".form-two").css("display", "none");
            $(".form-one").css("display", "block");
            setTimeout(function () {
                $(".form-one").css(show);
            }, 50);

        }, 200);
    })

    $(".tab-but-one").click(function () {
        $(".form-one").css(hiden);
        setTimeout(function () {
            $(".form-one").css("display", "none");
            $(".form-two").css("display", "block");
            setTimeout(function () {
                $(".form-two").css(show);
            }, 50);

        }, 200);
    })
})