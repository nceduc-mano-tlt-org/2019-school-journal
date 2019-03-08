Vue.component('project-list', {
    props: ['projectId','projectName'],
    template:
        '<div class="card d-inline-block mt-2" style="width: 18rem;">\n' +
        '  <img src="/assets/i/no-image.png" class="card-img-top" alt="">\n' +
        '  <div class="card-body">\n' +
        '   <button type="button" class="close d-none" aria-label="Close" onclick="vm.deleteProject(this)">\n' +
        '       <span aria-hidden="true">&times;</span>\n' +
        '   </button>'+
        '    <h5 class="card-title"><b>{{projectName}}</b></h5>\n' +
        '    <h6 class="d-none" >ID: <b>{{projectId}}</b></h6>\n' +
        '    <p class="card-text d-none"><span class="font-weight-bold">Project owner:</span>\n' +
        '      <span class="badge badge-primary bgc-primary">projectOwner</span>\n' +
        '    </p>\n' +
        '    <p class="card-text d-none"><span class="font-weight-bold">Project description:</span>\n' +
        '      <br>projectDescription \n' +
        '    </p>\n' +
        '    <button type="button" class="btn textc-white bgc-primary" onClick="vm.openEditProject(this)"  data-toggle="modal" id data-target="#editProjectModal">Manage</button>\n' +
        '    <a href="/section/" class="btn textc-white bgc-primary">Enter</a>\n' +
        '  </div>\n' +
        '</div>'
});


var vm = new Vue({
    el: '#app',
    data: {
        projects: [{
            id: 1,
            nameProject: 'My journey with Vue'  
        }] //TODO: del after API became works
    },
    mounted() {
        //axios.get('/api/v1/project/').then(response => (this.projects = response.data));
        this.projects [{
            id: 1,
            nameProject: 'My journey with Vue'  
        }]
    },
    methods: {
        loadProject: function () {
                axios
                    .get('/api/v1/project/')
                    .then(response => (this.projects = response.data));
        },
        addProject: function () {
            axios.post('/api/v1/project/', {
                id: "1",
                name: document.getElementById("add_person_name").value,
                lastName: document.getElementById("add_person_last_name").value,
                cars: []
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });
            this.loadData();
        },
        editProject: function () {
            axios.put('/api/v1/project/', {
                id: document.getElementById("edit_person_id").value,
                name: document.getElementById("edit_person_name").value,
                lastName: document.getElementById("edit_person_last_name").value,
                cars: []
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        deleteProject: function (element) {
            var button = element;
            personId = button.parentNode.getElementsByTagName("h5")[0].getElementsByTagName("b")[0].innerText;

            axios.delete('/api/v1/project/'+personId, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadData(), 1000);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        openEditProject: function (element) {
            var button = element;
            personId = button.parentNode.getElementsByTagName("h5")[0].getElementsByTagName("b")[0].innerText;
            personName = button.parentNode.getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            personLastName = button.parentNode.getElementsByTagName("h6")[1].getElementsByTagName("b")[0].innerText;
            document.getElementById("edit_person_id").value = personId;
            document.getElementById("edit_person_name").value = personName;
            document.getElementById("edit_person_last_name").value = personLastName;

        }
    }
});
