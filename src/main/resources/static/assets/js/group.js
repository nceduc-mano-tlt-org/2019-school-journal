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
        '   </button>' +
        '    <div class="card-info mb-2">' +
        '      <h5>Group description: </h5>' +
        '      <b>{{group.description}}</b>' +
        '    </div>\n' +
        '    <button type="button" class="btn textc-white bgc-primary" onClick="vm.openEditGroup(this)"  data-toggle="modal" id data-target="#editGroupModal">Manage group</button>\n' +
        '    <a :href="\'/group.html?group_id=\' + group.id" class="btn textc-white bgc-primary">Enter</a>\n' +
        '  </div>\n' +
        '</div>'
});

Vue.component('teacher-list', {
    props: ['teacher'],
    template:
        '<div class="card textc-black mt-3">\n' +
        '  <div class="card-header">\n' +
        '    <h6 class="d-none">Teacher Id: <b>{{teacher.id}}</b></h6>\n' +
        '    <h6 class="d-none">Teacher Group Id : <b>{{teacher.groupId}}</b></h6>\n' +
        '    <h5 class="card-title">{{teacher.firstName}} {{teacher.lastName}}</h5>\n' +
        '  </div>\n' +
        '  <div class="card-body">\n' +
        '   <button type="button" class="close" aria-label="Close" onclick="vm.deleteTeacher(this)">\n' +
        '       <span aria-hidden="true">&times;</span>\n' +
        '   </button>' +
        '    <button type="button" class="btn textc-white bgc-primary" onClick="vm.openEditTeacher(this)"  data-toggle="modal" id data-target="#editTeacherModal">Manage teacher</button>\n' +
    //    '    <a :href="\'/teacher.html?id=\' + teacher.id" class="btn textc-white bgc-primary">Enter</a>\n' +
        '  </div>\n' +
        '</div>'
});

Vue.component('student-list', {
    props: ['student'],
    template:
        '<div class="card textc-black mt-3">\n' +
        '  <div class="card-header">\n' +
        '    <h6 class="d-none">Student Id: <b>{{student.id}}</b></h6>\n' +
        '    <h6 class="d-none">Student Group Id : <b>{{student.groupId}}</b></h6>\n' +
        '    <h5 class="card-title">{{student.firstName}} {{student.lastName}}</h5>\n' +
        '  </div>\n' +
        '  <div class="card-body">\n' +
        '   <button type="button" class="close" aria-label="Close" onclick="vm.deleteStudent(this)">\n' +
        '       <span aria-hidden="true">&times;</span>\n' +
        '   </button>' +
        '    <button type="button" class="btn textc-white bgc-primary" onClick="vm.openEditStudent(this)"  data-toggle="modal" id data-target="#editStudentModal">Manage student</button>\n' +
        '    <a :href="\'/student.html?id=\' + student.id" class="btn textc-white bgc-primary">Enter</a>\n' +
        '  </div>\n' +
        '</div>'
});

var vm = new Vue({
    el: '#app',
    data: {
        switcher: false,
        groupName: '',
        sectionName: '',
        groups: [],
        teachers: [],
        students: []
    },
    mounted() {
        this.checkParams();
    },
    methods: {
        checkParams: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.has("section_id")) {
                this.switcher = true;
                this.showSectionName();
                this.loadGroup();
                //document.getElementById("show_group_count_in_tree").value = this.groups.length;
            }
            if (url.searchParams.has("group_id")) {
                this.switcher = false;
                this.showGroupName();
                this.loadPerson();
                document.getElementById("show_teacher_count_in_tree").value = this.teachers.length;
                document.getElementById("show_student_count_in_tree").value = this.students.length;
            }
        },
        loadGroup: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("section_id") !== '') {
                axios
                    .get('/api/v1/group/by-section/' + url.searchParams.get("section_id"))
                    .then(response => {
                        this.groups = response.data;
                        document.getElementById("show_group_count_in_tree").value = this.groups.length;
                    });
            }
        },
        addGroup: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("section_id") !== '') {
                axios.post('/api/v1/group/', {
                    id: "1",
                    name: document.getElementById("add_group_name").value,
                    description: document.getElementById("add_group_description").value,
                    sectionId: url.searchParams.get("section_id")
                })
                    .then(function (response) {
                        console.log(response);
                        setTimeout(vm.checkParams(), 300);
                    })
                    .then(function (){
                        document.getElementById("add_group_name").value = '';
                        document.getElementById("add_group_description").value = '';
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
                    setTimeout(vm.checkParams(), 300);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        deleteGroup: function (element) {
            var groupId = element.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            axios.delete('/api/v1/group/' + groupId, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.checkParams(), 300);
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
        },
        showGroupName: function () {
            var url = new URL(window.location.href);
            axios.get('/api/v1/group/' + url.searchParams.get("group_id"))
                .then(function (response) {
                    this.groupName = response.data.name;
                    document.getElementById("show_group_name").value = this.groupName;
                    document.getElementById("show_group_name_in_tree_1").value = this.groupName;
                    document.getElementById("hidden_section_id").href = '/group.html?section_id=' + response.data.sectionId;
                    axios.get('/api/v1/section/' + response.data.sectionId)
                        .then(function(response){
                            document.getElementById("show_section_name_in_tree_1").value = response.data.name;
                            axios.get('/api/v1/project/current/')
                                .then(function(response){
                                    document.getElementById("show_project_name_in_tree_1").value = response.data[0].name;
                                })
                        })
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        showSectionName: function(){
            var url = new URL(window.location.href);
            axios.get('/api/v1/section/' + url.searchParams.get("section_id"))
                .then(function (response) {
                    this.sectionName = response.data.name;
                    document.getElementById("show_section_name").value = this.sectionName;
                    document.getElementById("show_section_name_in_tree").value = this.sectionName;
                    axios.get('/api/v1/project/current/')
                        .then(function(response){
                            document.getElementById("show_project_name_in_tree").value = response.data[0].name;
                        })
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        loadPerson: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("group_id") !== '') {
                axios
                    .get('/api/v1/teacher/by-group/' + url.searchParams.get("group_id"))
                    .then(response => {
                        this.teachers = response.data;
                        document.getElementById("show_teacher_count_in_tree").value = this.teachers.length;
                    });
                axios
                    .get('/api/v1/student/by-group/' + url.searchParams.get("group_id"))
                    .then(response => {
                        this.students = response.data;
                        document.getElementById("show_student_count_in_tree").value = this.students.length;
                    });
            }
        },
        addTeacher: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("group_id") !== '') {
                axios.post('/api/v1/teacher/', {
                    id: "1",
                    firstName: document.getElementById("add_teacher_first_name").value,
                    lastName: document.getElementById("add_teacher_last_name").value,
                    groupId: url.searchParams.get("group_id")
                })
                    .then(function (response) {
                        console.log(response);
                        setTimeout(vm.checkParams(), 300);
                    })
                    .then(function (){
                        document.getElementById("add_teacher_first_name").value = '';
                        document.getElementById("add_teacher_last_name").value = '';
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        editTeacher: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("group_id") !== '') {
                axios.put('/api/v1/teacher/', {
                    id: document.getElementById("edit_teacher_id").value,
                    firstName: document.getElementById("edit_teacher_first_name").value,
                    lastName: document.getElementById("edit_teacher_last_name").value,
                })
                    .then(function (response) {
                        console.log(response);
                        setTimeout(vm.checkParams(), 1000);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        deleteTeacher: function (element) {
            var teacherId = element.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            axios.delete('/api/v1/teacher/' + teacherId, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.checkParams(), 300);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        openEditTeacher: function (element) {
            var button = element;
            var teacherId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            var teacherGroupId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[1].getElementsByTagName("b")[0].innerText;
            var teacherFirstName = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h5")[0].innerText.split(' ')[0];
            var teacherLastName = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h5")[0].innerText.split(' ')[1];

            document.getElementById("edit_teacher_id").value = teacherId;
            document.getElementById("edit_teacher_first_name").value = teacherFirstName;
            document.getElementById("edit_teacher_last_name").value = teacherLastName;
            document.getElementById("edit_teacher_group_id").value = teacherGroupId;
        },
        addStudent: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("group_id") !== '') {
                axios.post('/api/v1/student/', {
                    id: "1",
                    firstName: document.getElementById("add_student_first_name").value,
                    lastName: document.getElementById("add_student_last_name").value,
                    groupId: url.searchParams.get("group_id")
                })
                    .then(function (response) {
                        console.log(response);
                        setTimeout(vm.checkParams(), 300);
                    })
                    .then(function (){
                        document.getElementById("add_student_first_name").value = '';
                        document.getElementById("add_student_last_name").value = '';
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        editStudent: function () {
            var url = new URL(window.location.href);
            if (url.searchParams.get("group_id") !== '') {
                axios.put('/api/v1/student/', {
                    id: document.getElementById("edit_student_id").value,
                    firstName: document.getElementById("edit_student_first_name").value,
                    lastName: document.getElementById("edit_student_last_name").value,
                })
                    .then(function (response) {
                        console.log(response);
                        setTimeout(vm.checkParams(), 1000);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        deleteStudent: function (element) {
            var studentId = element.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            axios.delete('/api/v1/student/' + studentId, {})
                .then(function (response) {
                    console.log(response);
                    setTimeout(vm.checkParams(), 300);
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        openEditStudent: function (element) {
            var button = element;
            var studentId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[0].getElementsByTagName("b")[0].innerText;
            var studentGroupId = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h6")[1].getElementsByTagName("b")[0].innerText;
            var studentFirstName = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h5")[0].innerText.split(' ')[0];
            var studentLastName = button.parentElement.parentNode.getElementsByTagName("div")[0].getElementsByTagName("h5")[0].innerText.split(' ')[1];

            document.getElementById("edit_student_id").value = studentId;
            document.getElementById("edit_student_first_name").value = studentFirstName;
            document.getElementById("edit_student_last_name").value = studentLastName;
            document.getElementById("edit_student_group_id").value = studentGroupId;
        }

        // loadAttendance: function (element) {
        //         //     var url = new URL(window.location.href);
        //         //     if (url.searchParams.get("group_id") !== '') {
        //         //         axios
        //         //             .get('api/v1/group/attendance', {
        //         //                 url.searchParams.get("group_id")
        //         //             })
        //         //
        //         // }


    }
});