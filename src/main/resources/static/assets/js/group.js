Vue.component('group-list', {
    props: ['group'],
    template:
        '<div class="card textc-black mt-3">\n' +
        '  <div class="card-header">\n' +
        '    <h6 class="d-none">Group Id: <b>{{group.id}}</b></h6>\n' +
        '    <h6 class="d-none">Group Section Id : <b>{{group.sectionId}}</b></h6>\n' +
        '    <h5 class="card-title">{{group.name}}</h5>\n' +
        '  </div>\n' +
        '  <div class="card-body">\n' +
        '   <button type="button" class="close" aria-label="Close" onclick="vm.deleteGroup(this)">\n' +
        '       <span aria-hidden="true">&times;</span>\n' +
        '   </button>'+
        '    <div class="card-info mb-2">'+
        '      <h5>Group description: </h5>'+
        '      <b>{{group.description}}</b>'+
        '    </div>\n' +
        '    <button type="button" class="btn textc-white bgc-primary" onClick="vm.openEditGroup(this)"  data-toggle="modal" id data-target="#editGroupModal">Manage group</button>\n' +
        '    <a :href="\'/group.html?id=\' + group.id" class="btn textc-white bgc-primary">Enter</a>\n' +
        '  </div>\n' +
        '</div>'
});


var vm = new Vue({
    el: '#app',
    data: {
        groups: []
    },
    mounted() {
        var url_string = window.location.href;
        var url = new URL(url_string);
        if (url.searchParams.get("section_id")!=''){
            axios
                .get('/api/v1/group/by-section/'+ url.searchParams.get("section_id"))
                .then(response => (this.groups = response.data));
        } else if (rl.searchParams.get("id")!=''){
            axios
                .get('/api/v1/section/')
                .then(response => (this.groups = response.data));
        }
    },
    methods: {
        loadGroup: function () {
            var url_string = window.location.href;
            var url = new URL(url_string);
            if (url.searchParams.get("section_id")!=''){
                axios
                    .get('/api/v1/group/by-section/'+ url.searchParams.get("section_id"))
                    .then(response => (this.groups = response.data));
            } else if (rl.searchParams.get("id")!=''){
                axios
                    .get('/api/v1/section/')
                    .then(response => (this.groups = response.data));
            }
        },
        addGroup: function () {
            var url_string = window.location.href;
            var url = new URL(url_string);
            if (url.searchParams.get("section_id")!='') {
                axios.post('/api/v1/group/', {
                    id: "1",
                    name: document.getElementById("add_group_name").value,
                    description: document.getElementById("add_group_description").value,
                    sectionId: url.searchParams.get("section_id")
                })
                    .then(function (response) {
                        console.log(response);
                        setTimeout(vm.loadGroup(), 300);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        editGroup: function () {
            axios.put('/api/v1/group/', {
                id: document.getElementById("edit_group_id").value,
                name: document.getElementById("edit_group_name").value,
                description: document.getElementById("edit_group_description").value,
                sectionId: document.getElementById("edit_group_section_id").value
            })
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadGroup(), 300);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        deleteGroup: function (element) {
            var button = element;
            var groupId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            axios.delete('/api/v1/group/'+groupId, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.loadGroup(), 300);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        openEditGroup: function (element) {
            var button = element;
            var groupId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            var groupSectionId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[1].getElementsByTagName("b")[0].innerText;
            var groupName = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h5")[0].innerText;
            var groupDescription = button.parentNode.getElementsByTagName("div")[0].getElementsByTagName("b")[0].innerText;

            document.getElementById("edit_group_id").value = groupId;
            document.getElementById("edit_group_name").value = groupName;
            document.getElementById("edit_group_description").value = groupDescription;
            document.getElementById("edit_group_section_id").value = groupSectionId;

        }
    }
});