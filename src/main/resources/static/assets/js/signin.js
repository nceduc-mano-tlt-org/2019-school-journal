var vm = new Vue({
    el: '#app',
    data: {
        user: []
    },
    methods: {
        signIn: function (element) {
                axios.post('/api/v1/signin/', {
                    username: document.getElementById("input-username").value,
                    password: document.getElementById("input-password").value,
                })
                .then(function (response) {
                    console.log(response);
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
