$(document).ready(function() {

    $(".createUser").click(function() {
        var sectionData = $(this).parents("form").serialize();
        $.ajax({
            url: "/user/create",
            type: "POST",
            data: userData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        });
    });

    $(".updateUser").click(function() {
        var sectionData = $(this).parents("form").serialize();
        $.ajax({
            url: "/user/update",
            type: "PUT",
            data: userData,
            dataType: "JSON",
            success: function(response) {
               location.reload();
            }
        });
    });

    $(".deletUser").click(function() {
        var sectionData = $(this).parents("form").serialize();
        $.ajax({
            url: "/user/delete",
            type: "DELETE",
            data: userData,
            dataType: "JSON",
            success: function(response) {
                location.reload();
            }
        });
    });

});