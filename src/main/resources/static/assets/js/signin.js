var vm = new Vue({
    el: '#app',
    data: {
        user: []
    },
    methods: {
        signIn: function (element) {
                axios.post('/api/v1/signin/', {
                    login: document.getElementById("inputLogin").value,
                    password: document.getElementById("inputPassword").value,
                })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });
                this.checkSignIn();
        },
        checkSignIn: function () {
            console.log('Do some signIn check');
            //window.location.href = "/project";
        }
    }
});
