Vue.component('project-list', {
    props: ['project'],
    template:
        '<div class="card d-inline-block mt-2" style="width: 18rem;">\n' +
        '  <img src="/assets/i/no-image.png" class="card-img-top" alt="">\n' +
        '  <div class="card-body">\n' +
        '   <button type="button" class="close d-none" aria-label="Close" onclick="vm.deleteProject(this)">\n' +
        '       <span aria-hidden="true">&times;</span>\n' +
        '   </button>'+
        '    <h6 class="d-none" >Project ID: <b>{{project.id}}</b></h6>\n' +
        '    <h5 class="card-title"><b>{{project.name}}</b></h5>\n' +
        '    <h6 class="d-none" >Owner ID: <b>{{project.userId}}</b></h6>\n' +
        '    <p class="card-text d-none"><span class="font-weight-bold">Project owner:</span>\n' +
        '      <span class="badge badge-primary bgc-primary">User name</span>\n' + // TODO: display username
        '    </p>\n' +
        '    <p class="card-text d-none"><span class="font-weight-bold">Project description:</span>\n' +
        '      <br>projectDescription \n' +
        '    </p>\n' +
        '    <button type="button" class="btn textc-white bgc-primary" onClick="vm.openEditProject(this)"  data-toggle="modal" id data-target="#editProjectModal">Manage</button>\n' +
        '    <a :href="\'/section.html?project_id=\' + project.id" class="btn textc-white bgc-primary">Enter</a>\n' +
        '  </div>\n' +
        '</div>'
});


var vm = new Vue({
    el: '#app',
    data: {
        projects: []
    },
    mounted() {
        axios.get('/api/v1/project/current/').then(response => (this.projects = response.data));
    },
    methods: {
        loadProject: function () {
                axios.get('/api/v1/project/current/').then(response => (this.projects = response.data));
        },
        addProject: function () {
            axios.post('/api/v1/project/', {
                id: document.getElementById("add_project_id").value,
                name: document.getElementById("add_project_name").value,
                //userId: document.getElementById("add_project_owner").value
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadProject(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });
            this.loadData();
        },
        editProject: function () {
            axios.put('/api/v1/project/', {
                id: document.getElementById("edit_project_id").value,
                name: document.getElementById("edit_project_name").value,
                //userId: document.getElementById("edit_project_owner").value
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadProject(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        deleteProject: function (element) {
            var button = element;
            var projectId = button.parentNode.getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;

            axios.delete('/api/v1/project/'+ projectId, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadProject(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        openEditProject: function (element) {
            var button = element;
            var projectId = button.parentNode.getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            var projectName = button.parentNode.getElementsByTagName("h5")[0].getElementsByTagName("b")[0].innerText;
            var projectOwner = button.parentNode.getElementsByTagName("h6")[1].getElementsByTagName("b")[0].innerText;
            document.getElementById("edit_project_id").value = projectId;
            document.getElementById("edit_project_name").value = projectName;
            document.getElementById("edit_project_owner").value = projectOwner;

        }
    }
});
