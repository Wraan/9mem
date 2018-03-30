$("document").ready(function () {

    $(".points").html(function () {
        var parentId = $(this).parent().parent().attr('id');
        var voteValueId = $(this).attr('id');

        $.ajax({
            type: "GET",
            url: "/api/votesValue/" + parentId,
            success: function (data) {
                $('#' + voteValueId).text(data + ' points')
            }
        });
    });

    // $(".commentPoints").html(function () {
    //     var commentId = $(this).parent().parent().attr('id');
    //     var voteValueId = $(this).attr('id');
    //
    //     $.ajax({
    //         type: "GET",
    //         url: "/api/votesValue/" + commentId,
    //         success: function (data) {
    //             $('#' + voteValueId).text(data + ' points')
    //         }
    //     });
    // });

    $(".voteUp").html(function () {
        var parentId = $(this).parent().parent().parent().attr('id');
        var voteBtnId = $(this).attr('id');

        $.ajax({
            type: "GET",
            url: "/api/userVote/" + parentId,
            success: function (data) {
                if (data === 1){
                    $('#' + voteBtnId).addClass('btn-primary')
                }
                else{
                    $('#' + voteBtnId).addClass('btn-secondary')
                }
            }
        });
    });

    $(".voteDown").html(function () {
        var parentId = $(this).parent().parent().parent().attr('id');
        var voteBtnId = $(this).attr('id');

        $.ajax({
            type: "GET",
            url: "/api/userVote/" + parentId,
            success: function (data) {
                if (data === -1){
                    $('#' + voteBtnId).addClass('btn-danger')
                }
                else{
                    $('#' + voteBtnId).addClass('btn-secondary')
                }
            }
        });
    });
});