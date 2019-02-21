$(document).ready(function() {

    $(".createProject").click(function() {
        var groupData = $(this).parents("form").serialize();
        $.ajax({
            url: "/project/create",
            type: "POST",
            data: projectData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        });
    });

    $(".updateProject").click(function() {
        var groupData = $(this).parents("form").serialize();
        $.ajax({
            url: "/project/update",
            type: "PUT",
            data: projectData,
            dataType: "JSON",
            success: function(response) {
               location.reload();
            }
        });
    });

    $(".deleteProject").click(function() {
        var groupData = $(this).parents("form").serialize();
        $.ajax({
            url: "/project/delete",
            type: "DELETE",
            data: projectData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        });
    });

});