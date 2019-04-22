Vue.component('section-list', {
    props: ['section'],
    template:
        '<div class="card textc-black mt-3">\n' +
        '  <div class="card-header">\n' +
        '    <h6 class="d-none">Section Id: <b>{{section.id}}</b></h6>\n' +
        '    <h6 class="d-none">Section Project Id: <b>{{section.projectId}}</b></h6>\n' +
        '    <h5 class="card-title">{{section.name}}</h5>\n' +
        '  </div>\n' +
        '  <div class="card-body">\n' +
        '    <div class="card-info mb-2">' +
        '      <h5>Section description:</h5>' +
        '      <span>{{section.description}} </span>' +
        '      </p>\n' +
        '    </div>\n' +
        '   </div>' +
        '  <div class="card-footer">\n' +
        '    <button type="button" class="btn textc-white bgc-primary" onClick="vm.openEditSection(this)"  data-toggle="modal" id data-target="#editSectionModal">Manage section</button>\n' +
        '    <a v-bind:href="\'/group.html?section_id=\' + section.id" class="btn textc-white bgc-primary">Enter</a>\n' +
        '   <button class="btn textc-white bgc-primary" data-toggle="modal" style="float: right" data-target="#confirmDeleting" onclick="vm.openConfirmDeletion(this)">Delete</button>' +
        '  </div>\n' +
        '</div>'
});


    var vm = new Vue({
    el: '#app',
    data: {
        projectName: '',
        userId: '',
        sections: []
    },
    mounted() {
        this.loadSection();
        this.showProjectName();
    },
    methods: {
        showProjectName: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("project_id") !== '') {
                axios.get('/api/v1/project/current/')
                    .then(function (response) {
                        this.projectName = response.data[0].name;
                        document.getElementById("show_project_name").value = this.projectName;
                        document.getElementById("show_project_name_in_tree").value = this.projectName;
                        this.userId = response.data[0].userId;
                        axios.get('/api/v1/user/' + this.userId)
                            .then(function (response) {
                                document.getElementById("show_username").value = response.data.username;
                            })
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        loadSection: function () {
            var url_string = window.location.href;
            var url = new URL(url_string);
            if (url.searchParams.get("project_id") !== '') {
                axios
                    .get('/api/v1/section/by-project/' + url.searchParams.get("project_id"))
                    .then(response => {
                        this.sections = response.data;
                        document.getElementById("show_section_count_in_tree").value = this.sections.length;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            } else if (url.searchParams.get("id") !== '') {
                axios
                    .get('/api/v1/section/')
                    .then(response => (this.sections = response.data));
            }
        },
        addSection: function () {
            axios.post('/api/v1/section/', {
                id: "1",
                name: document.getElementById("add_section_name").value,
                description: document.getElementById("add_section_description").value
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadSection(), 300);
                })
                .then(function (){
                    document.getElementById("add_section_name").value = '';
                    document.getElementById("add_section_description").value = '';
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        editSection: function () {
            axios.put('/api/v1/section/', {
                id: document.getElementById("edit_section_id").value,
                name: document.getElementById("edit_section_name").value,
                description: document.getElementById("edit_section_description").value,
                projectId: document.getElementById("edit_section_project_id").value
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadSection(), 300);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        deleteSection: function (element) {
            axios.delete('/api/v1/section/' + document.getElementById("delete_entity_id").value, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadSection(), 300);
                })
                .catch(function (error) {
                    if (error.response.status === 500) {
                        $("#notempty").click();
                    }
                });

        },
        openEditSection: function (element) {
            var button = element;
            var sectionId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            var sectionProjectId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[1].getElementsByTagName("b")[0].innerText;
            var sectionName = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h5")[0].innerText;
            var sectionDescription = button.parentElement.parentNode.getElementsByTagName("div")[1].getElementsByTagName("span")[0].innerText;

            document.getElementById("edit_section_id").value = sectionId;
            document.getElementById("edit_section_name").value = sectionName;
            document.getElementById("edit_section_description").value = sectionDescription;
            document.getElementById("edit_section_project_id").value = sectionProjectId;
        },
        openConfirmDeletion: function(element){
            document.getElementById("delete_entity_id").value = element.parentElement.parentNode
                .getElementsByTagName("div")[0]
                .getElementsByTagName("h6")[0]
                .getElementsByTagName("b")[0]
                .innerText;
        }
    }
});