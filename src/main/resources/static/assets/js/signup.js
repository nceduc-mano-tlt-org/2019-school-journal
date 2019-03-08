var vm = new Vue({
    el: '#app',
    data: {
        user: []
    },
    methods: {
        signUp: function (element) {
            if (document.getElementById("termsAgree").checked == true) {
                axios.post('/api/v1/user/signup/', {
                    username: document.getElementById("input-username").value,
                    password: document.getElementById("input-password").value,
                })
                .then(function (response) {
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
                this.checkSignUp();
            }
        },
        checkSignUp: function () {
            console.log('Do some signUP check');
            //window.location.href = "/signin";
        }
    }
});
