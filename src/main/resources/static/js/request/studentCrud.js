$(document).ready(function () {
    $(".createStudent").click(function () {
        var studentData = $(this).parents("form").serialize()
        $.ajax({
            url: "/student/create",
            type: "POST",
            data: studentData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        })
    });

    $(".updateStudent").click(function () {
        var studentData = $(this).parents("form").serialize()
        $.ajax({
            url: "/student/update",
            type: "PUT",
            data: studentData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        })
    });

    $(".deleteStudent").click(function () {
        var studentData = $(this).parents("form").serialize()
        $.ajax({
            url: "/student/delete",
            type: "DELETE",
            data: studentData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        })
    })
});