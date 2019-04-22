var vm = new Vue({
    el: '#app',
    data: {
        user: []
    },
    methods: {
        checkEmpty: function (element) {
            let login = document.getElementById("input-username").value;
            let pass = document.getElementById("input-password").value;
            if (login === "" || pass === "") {
                $("#checkempty").click();
            } else {
                this.signIn(element);
            }
        },
        signIn: function (element) {
            axios.post('/api/v1/user/signin/', {
                    username: document.getElementById("input-username").value,
                    password: document.getElementById("input-password").value,
                })
                .then(function (response) {
                    console.log(response);
                    if (response.status === 200) {
                        window.location.href = "/project.html";
                    }
                })
                .catch(function (error) {
                    if (error.response.status === 403) {
                        document.getElementById("input-username").value = "";
                        document.getElementById("input-password").value = "";

                        $("#wrongpass").click();
                    }
                });
        }
    }
});
