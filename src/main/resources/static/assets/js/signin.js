var vm = new Vue({
    el: '#app',
    data: {
        user: []
    },
    methods: {
        signIn: function (element) {
            axios.post('/api/v1/user/signin/', {
                    username: document.getElementById("input-username").value,
                    password: document.getElementById("input-password").value,
                })
                .then(function (response) {
                    console.log(response);
                    if (response.status == 200) {
                        window.location.href = "/project.html";
                    } else {
                        // TODO: make marker
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
});
