var vm = new Vue({
    el: '#app',
    data: {
        user: [],
    },
    methods: {
        checkConfirm: function (element) {
            let pass = document.getElementById("input-password").value;
            let confPass = document.getElementById("confirm-password").value;
            if (pass !== confPass) {
                document.getElementById("message").value = "Passwords don't match!";
                document.getElementById("input-password").value = "";
                document.getElementById("confirm-password").value = "";
            } else {
                this.checkEmpty(element);
            }
        },

        checkEmpty: function (element) {
            let login = document.getElementById("input-username").value;
            let email = document.getElementById("input-email").value;
            let pass = document.getElementById("input-password").value;
            let confPass = document.getElementById("confirm-password").value;
            if (login === "" || email === "" || pass === "" || confPass === "") {
                document.getElementById("message").value = "Not all fields are filled!";
            } else {
                this.signUp(element);
            }
        },

        signUp: function (element) {
            axios.post('/api/v1/user/signup/', {
                username: document.getElementById("input-username").value,
                password: document.getElementById("input-password").value,
            })
                .then(function (response) {
                    if (response.status === 201) {
                        window.location.href = "/signin.html";
                    }
                })
                .catch(function (error) {
                    if (error.response.status === 400) {
                        document.getElementById("input-username").value = "";
                        document.getElementById("input-email").value = "";
                        document.getElementById("input-password").value = "";
                        document.getElementById("confirm-password").value = "";
                        document.getElementById("message").value = "This user already exists!";
                    }
                })
        }
    }
});
