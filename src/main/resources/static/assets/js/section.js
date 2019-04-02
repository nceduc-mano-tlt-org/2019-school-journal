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
        '   <button type="button" class="close" aria-label="Close" onclick="vm.deleteSection(this)">\n' +
        '       <span aria-hidden="true">&times;</span>\n' +
        '   </button>' +
        '    <div class="card-info mb-2">' +
        '      <h5>Section description</h5>' +
        '      <b>{{section.description}}</b>' +
        '      <p class="d-none">Groups in this section:</p>\n' +
        '      <p class="card-text d-none">\n' +
        '        <a href="/group/1" class="badge textc-white bgc-primary">Default 1</a>\n' +
        '        <a href="/group/2" class="badge textc-white bgc-primary">Default 2</a>\n' +
        '        <a href="/section/1" class="badge textc-black">see all...</a>\n' +
        '      </p>\n' +
        '    </div>\n' +
        '    <button type="button" class="btn textc-white bgc-primary" onClick="vm.openEditSection(this)"  data-toggle="modal" id data-target="#editSectionModal">Manage section</button>\n' +
        '    <a v-bind:href="\'/group.html?section_id=\' + section.id" class="btn textc-white bgc-primary">Enter</a>\n' +
        '  </div>\n' +
        '</div>'
});


var vm = new Vue({
    el: '#app',
    data: {
        projectName: '',
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
                axios.get('/api/v1/project/' + url.searchParams.get("project_id"))
                    .then(function (response) {
                        this.projectName = response.data.name;
                        document.getElementById("show_project_name").value = this.projectName;
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
                    .then(response => (this.sections = response.data));
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
            var sectionId = element.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            axios.delete('/api/v1/section/' + sectionId, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadSection(), 300);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        openEditSection: function (element) {
            var button = element;
            var sectionId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            var sectionProjectId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[1].getElementsByTagName("b")[0].innerText;
            var sectionName = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h5")[0].innerText;
            var sectionDescription = button.parentNode.getElementsByTagName("div")[0].getElementsByTagName("b")[0].innerText;

            document.getElementById("edit_section_id").value = sectionId;
            document.getElementById("edit_section_name").value = sectionName;
            document.getElementById("edit_section_description").value = sectionDescription;
            document.getElementById("edit_section_project_id").value = sectionProjectId;

        }
    }
});