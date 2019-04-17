
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
                        document.getElementById("show_student_name_in_tree").value = this.studentName;
                        axios.get('/api/v1/group/' + response.data.groupId)
                            .then(function (response) {
                                document.getElementById("show_group_name_in_tree").value = response.data.name;
                                document.getElementById("hidden_group_id").href = '/group.html?group_id=' + response.data.id;
                                document.getElementById("hidden_section_id").href = '/group.html?section_id=' + response.data.sectionId;

                                axios.get('/api/v1/section/' + response.data.sectionId)
                                    .then(function(response){
                                        document.getElementById("show_section_name_in_tree").value = response.data.name;

                                        axios.get('/api/v1/project/current/')
                                            .then(function(response){
                                                document.getElementById("show_project_name_in_tree").value = response.data[0].name;
                                            })
                                    })
                            })
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    }
});