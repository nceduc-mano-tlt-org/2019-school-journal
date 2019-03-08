var vm = new Vue({
    el: '#app',
    data: {
        user: []
    },
    methods: {
        signUp: function (element) {
            if (document.getElementById("termsAgree").checked == true) {
                axios.post('/api/v1/signup/', {
                    username: document.getElementById("inputLogin").value,
                    password: document.getElementById("inputPassword").value,
                })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
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
