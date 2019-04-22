var vm = new Vue({
    el: '#app',
    data: {
        studentName: '',
        userId: '',
        groupId: ''
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
                        document.getElementById("show_balance").value = response.data.walletBalance;

                        this.studentName = response.data.firstName + ' ' + response.data.lastName;
                        document.getElementById("show_student_name").value = this.studentName;
                        document.getElementById("show_student_name_in_tree").value = this.studentName;
                        axios.get('/api/v1/group/' + response.data.groupId)
                            .then(function (response) {
                                document.getElementById("show_group_name_in_tree").value = response.data.name;
                                document.getElementById("hidden_group_id").href = '/group.html?group_id=' + response.data.id;
                                document.getElementById("hidden_section_id").href = '/group.html?section_id=' + response.data.sectionId;
                                this.groupId = response.data.id;
                                axios.get('/api/v1/section/' + response.data.sectionId)
                                    .then(function (response) {
                                        document.getElementById("show_section_name_in_tree").value = response.data.name;
                                        document.getElementById("hidden_project_id").href = '/section.html?project_id=' + response.data.projectId;
                                        axios.get('/api/v1/project/current/')
                                            .then(function (response) {
                                                document.getElementById("show_project_name_in_tree").value = response.data[0].name;
                                                this.userId = response.data[0].userId;
                                                axios.get('/api/v1/user/' + this.userId)
                                                    .then(function (response) {
                                                        document.getElementById("show_username").value = response.data.username;
                                                    })
                                            })
                                    })
                            })
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        deposit: function (element) {
            axios.put('/api/v1/payment/deposit/', {
                studentId: new URL(window.location.href).searchParams.get("id"),
                amount: document.getElementById("deposit_amount").value
            })
                .then(function (response) {
                    if (response.status === 200) {
                        $("#succesdeposit").click();
                    }
                })
                .then(function () {
                    document.getElementById("deposit_amount").value = '';

                    setTimeout(vm.showName(), 300);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        transfer: function (element) {
            axios.get('/api/v1/student/' + new URL(window.location.href).searchParams.get("id"))
                .then(response => {
                    axios.post('/api/v1/payment/transfer/', {
                        amount: document.getElementById("transfer_amount").value,
                        groupId: response.data.groupId,
                        studentId: new URL(window.location.href).searchParams.get("id")
                    })
                        .then(function (response) {
                            if (response.status === 200) {
                                $("#succestransfer").click();
                            }
                        })
                        .then(function () {
                            document.getElementById("transfer_amount").value = '';

                            setTimeout(vm.showName(), 300);
                        })
                        .catch(function (error) {
                            console.log(error);
                            if (error.response.status === 400) {
                                $("#notenoughtransfer").click();
                            }
                        });
                });
        }
    }
});