var vm = new Vue({
    el: '#app',
    data: {
        teacherName: ''
    },
    mounted() {
        this.showName();
    },
    methods: {
        showName: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("id") !== '') {
                axios.get('/api/v1/teacher/' + url.searchParams.get("id"))
                    .then(function (response) {
                        this.teacherName = response.data.firstName + ' ' + response.data.lastName;
                        document.getElementById("show_teacher_name").value = this.teacherName;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    }
});
