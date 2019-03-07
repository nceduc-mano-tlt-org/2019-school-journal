$(document).ready(function () {
    $(".createTeacher").click(function () {
        var teacherData = $(this).parents("form").serialize()
        $.ajax({
            url: "/teacher/create",
            type: "POST",
            data: teacherData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        })
    });

    $(".updateTeacher").click(function () {
        var teacherData = $(this).parents("form").serialize()
        $.ajax({
            url: "/teacher/update",
            type: "PUT",
            data: teacherData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        })
    });

    $(".deleteTeacher").click(function () {
        var teacherData = $(this).parents("form").serialize()
        $.ajax({
            url: "/teacher/delete",
            type: "DELETE",
            data: teacherData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        })
    })
});