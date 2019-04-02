
var vm = new Vue({
    el: '#app',
    data: {
        studentName: ''
    },
    mounted() {
        this.showName();
    },
    methods: {
        showName: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("id") !== '') {
                axios.get('/api/v1/student/' + url.searchParams.get("id"))
                    .then(function (response) {
                        this.studentName = response.data.firstName + ' ' + response.data.lastName;
                        document.getElementById("show_student_name").value = this.studentName;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    }
});