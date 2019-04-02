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
                        document.getElementById("show_teacher_name_in_tree").value = this.teacherName;
                        axios.get('/api/v1/group/' + response.data.groupId)
                            .then(function (response) {
                                document.getElementById("show_group_name_in_tree").value = response.data.name;
                                document.getElementById("hidden_group_id").href = '/group.html?group_id=' + response.data.id;
                                document.getElementById("hidden_section_id").href = '/group.html?section_id=' + response.data.sectionId;

                                axios.get('/api/v1/section/' + response.data.sectionId)
                                    .then(function(response){
                                        document.getElementById("show_section_name_in_tree").value = response.data.name;

                                        axios.get('/api/v1/project/' + response.data.projectId)
                                            .then(function(response){
                                                document.getElementById("show_project_name_in_tree").value = response.data.name;
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
