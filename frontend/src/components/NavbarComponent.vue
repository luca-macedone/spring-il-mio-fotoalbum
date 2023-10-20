<template>
    <header class="container-fluid shadow" :class="reactiveData.modalView ? '' : 'mb-5'">
        <nav class="row justify-content-between align-items-center py-3 bg-dark">
            <div class="col text-light">
                <span class="text-decoration-none text-light fs-3 fw-bold text-uppercase">
                    Java Photographer
                    <span class="fw-light text-lowercase">| frontoffice</span>
                </span>
            </div>
            <div class="col d-flex justify-content-end align-items-center gap-1 gap-md-2">
                <RouterLink type="button" class="btn text-light p-1 p-md-2" to="/">Home</RouterLink>
                <!-- <RouterLink class="text-light text-decoration-none" to="/about">About</RouterLink> -->
                <a type="button" class="btn d-flex justify-content-center align-items-center gap-2 p-1 p-md-2"
                    href="http://localhost:8080/">
                    <box-icon name="user-circle" type="solid" class="fill_light"></box-icon>
                    <span class="text-light d-none d-md-inline-flex">
                        Dashboard
                    </span>
                </a>
                <button class="btn d-flex align-items-center justify-content-center gap-2 p-1 p-md-2"
                    @click="toggleModal()">
                    <box-icon name="message-square-dots" type="solid" class="fill_light"></box-icon>
                    <span class="text-light d-none d-md-inline-flex">
                        Contact Us
                    </span>
                </button>
            </div>
        </nav>
    </header>
    <div v-if="reactiveData.modalView">
        <form class="bg-secondary d-flex flex-column justify-content-between align-items-center p-5 mb-5">
            <div class="mb-3 w-100">
                <label for="email" class="form-label text-light fw-semibold fs-5 text-uppercase">Email</label>
                <input type="text" class="form-control rounded-0" name="email" id="email" aria-describedby="emailHelpId"
                    placeholder="mario@rossi.it" v-model="reactiveData.email">
                <small id="emailHelpId" class="form-text text-muted">
                    Please type your email
                </small>
            </div>
            <div class="mb-3 w-100">
                <label for="message" class="form-label text-light fw-semibold fs-5 text-uppercase">Message</label>
                <textarea class="form-control rounded-0" name="message" id="message" rows="3"
                    aria-describedby="messageHelpId" v-model="reactiveData.message"></textarea>
                <small id="messageHelpId" class="form-text text-muted">
                    Please type your message here
                </small>
            </div>
            <div class="align-self-center align-self-sm-end d-flex gap-3 align-items-center flex-wrap">
                <button class="btn btn-dark rounded-0 px-5" type="reset">
                    Reset
                </button>
                <button class="btn btn-dark rounded-0 px-5" @click.prevent="sendMessage()">
                    Send
                </button>
            </div>
        </form>
    </div>
    <div v-if="reactiveData.successMessageView" class="d-flex justify-content-center align-items-center w-100 mb-5">
        <span class="alert alert-info rounded-0 border-0 shadow">
            {{ reactiveData.successMessageText }}
        </span>
    </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { reactive } from 'vue';
import axios from "axios";

const base_url = "http://localhost:8080/api/v1"
const messages_endpoing = "/messages"
const reactiveData = reactive({
    modalView: false,
    successMessageView: false,
    successMessageText: "",
    email: "",
    message: ""
})

function toggleModal() {
    reactiveData.modalView = !reactiveData.modalView;
}

function sendMessage() {
    axios
        .post(base_url + messages_endpoing, { email: reactiveData.email, message: reactiveData.message })
        .then(response => {
            reactiveData.email = ""
            reactiveData.message = ""
            reactiveData.successMessageText = "Message sended with success!"
            success()
            console.log(response.status)
        })
        .catch(err => {
            reactiveData.successMessageText = "Sending message failed..."
            fail()
            console.error(err.message)
        })
}

function success() {
    reactiveData.modalView = false
    reactiveData.successMessageView = !reactiveData.successMessageView;
    setTimeout(() => {
        reactiveData.successMessageView = !reactiveData.successMessageView;
        reactiveData.successMessageText = ""
    }, 3000);

}

function fail() {
    reactiveData.modalView = false
    reactiveData.successMessageView = !reactiveData.successMessageView;
    setTimeout(() => {
        reactiveData.successMessageView = !reactiveData.successMessageView;
        reactiveData.successMessageText = ""
    }, 3000);


}
</script>

<style lang="scss" scoped></style>