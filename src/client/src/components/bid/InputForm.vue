<template>
  <div class="frm-bid">
    <b-alert show variant="danger" v-for="error in errors" :key="error">{{error}}</b-alert>
    <b-form @submit="onSubmit" v-if="show">
      <b-form-group label="Title" label-for="bid-name">
        <b-form-input id="bid-name" v-model="form.name" type="text" placeholder="Enter title" required></b-form-input>
      </b-form-group>
      <b-form-group label="Description" label-for="bid-description">
        <b-form-textarea id="bid-description" v-model="form.description" placeholder="Enter something..." rows="3" max-rows="6"></b-form-textarea>
      </b-form-group>
      <b-row>
        <b-col cols="4">
          <b-form-group label="Min" label-for="bid-min">
            <b-form-input id="bid-min" v-model="form.priceMin" type="number" placeholder="Min price" required></b-form-input>
          </b-form-group>
        </b-col>
        <b-col cols="4">
          <b-form-group label="Max" label-for="bid-max">
            <b-form-input id="bid-max" v-model="form.priceMax" type="number" placeholder="Max price"></b-form-input>
          </b-form-group>
        </b-col>
        <b-col cols="4">
          <b-form-group label="Step" label-for="bid-step">
            <b-form-input id="bid-step" v-model="form.priceStep" type="number" placeholder="Step value" required></b-form-input>
          </b-form-group>
        </b-col>
      </b-row>

      <b-row>
        <b-col cols="12" md="6">
          <b-form-group label="Start">
            <b-row>
              <b-col cols="8" aria-required="true">
                <b-form-datepicker v-model="form.start.date" placeholder="" required></b-form-datepicker>
              </b-col>
              <b-col cols="4" aria-required="true">
                <b-form-timepicker v-model="form.start.time" placeholder="" required></b-form-timepicker>
              </b-col>
            </b-row>
          </b-form-group>
        </b-col>
        <b-col cols="12" md="6">
          <b-form-group label="End">
            <b-row>
              <b-col cols="8" aria-required="true">
                <b-form-datepicker v-model="form.end.date" placeholder="" required></b-form-datepicker>
              </b-col>
              <b-col cols="4" aria-required="true">
                <b-form-timepicker v-model="form.end.time" placeholder="" required></b-form-timepicker>
              </b-col>
            </b-row>
          </b-form-group>
        </b-col>
      </b-row>
      <div class="text-center">
        <b-button class="ml-1 mr-1" type="submit" variant="primary" v-if="!$props.readOnly">Submit</b-button>
        <b-button class="ml-1 mr-1" type="button" variant="danger" v-if="$props.readOnly" @click="onRemove">Remove</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
  import api from '../../api'
  import moment from 'moment'
  import _ from 'lodash'
  export default {
    props: ['id', 'readOnly'],
    data() {
      return {
        form: {
          name: '',
          description: '',
          priceMin: null,
          priceMax: null,
          priceStep: null,
          start: {
            date: null,
            time: null
          },
          end: {
            date: null,
            time: null
          }
        },
        show: false,
        isUpdate: false,
        oldData: null,
        errors: []
      }
    },
    mounted() {
      this.isUpdate = this.$props.id != 0
      this.$log.debug(this.$props.readOnly)
      if (this.isUpdate) {
        api.bid.get(this.$props.id)
          .then(response => {
            if (response.status == 200) {
              this.oldData = response.data
              this.form.name = response.data.name
              this.form.description = response.data.description
              this.form.priceMin = response.data.priceMin
              this.form.priceMax = response.data.priceMax
              this.form.priceStep = response.data.priceStep
              this.form.start = {
                date: moment(response.data.startTime * 1000).format("YYYY-MM-DD"),
                time: moment(response.data.startTime * 1000).format("HH:mm:ss")
              }
              this.form.end = {
                date: moment(response.data.endTime * 1000).format("YYYY-MM-DD"),
                time: moment(response.data.endTime * 1000).format("HH:mm:ss")
              }
            }
          })
          .catch(error => {
            this.$log.debug(error)
          })
          .finally(() => {
            this.show = true;
          })
      } else {
        this.show = true;
      }
    },
    methods: {
      onSubmit(event) {
        event.preventDefault()
        this.errors = [];
        if (this.isUpdate) {
          this.oldData.name = this.form.name
          this.oldData.description = this.form.description
          this.oldData.priceMin = this.form.priceMin
          this.oldData.priceMax = this.form.priceMax
          this.oldData.priceStep = this.form.priceStep
          this.oldData.startTime = moment(this.form.start.date + ' ' + this.form.start.time).unix()
          this.oldData.endTime = moment(this.form.end.date + ' ' + this.form.end.time).unix()

          api.bid.update(this.$props.id, this.oldData)
            .then(response => {
              if (response.status == 200) {
                this.$log.debug(response.data)
                this.$router.push('/bid')
              }
            })
            .catch(error => {
              this.$log.debug(error)
            })
            .finally(() => {})
        } else {
          let data = {
            name: this.form.name,
            description: this.form.description,
            priceMin: this.form.priceMin,
            priceMax: this.form.priceMax,
            priceStep: this.form.priceStep,
            startTime: moment(this.form.start.date + ' ' + this.form.start.time).unix(),
            endTime: moment(this.form.end.date + ' ' + this.form.end.time).unix()
          }
          api.bid.create(data)
            .then(response => {
              if (response.status == 200) {
                this.$log.debug(response.data)
                this.$router.push('/bid')
              }
            })
            .catch(error => {
              if (error.response) {
                if (_.isObject(error.response.data)) {
                  Object.values(error.response.data).forEach(v => {
                    this.errors.push(v);
                  });
                } else if (_.isString(error.response.data)) {
                  this.errors.push(error.response.data);
                }
              }
            })
            .finally(() => {})
        }
      },
      onRemove(event) {
        event.preventDefault()
        api.bid.delete(this.$props.id)
          .then(response => {
            if (response.status == 200) {
              this.$router.push('/bid')
            }
          })
          .catch(error => {
            this.$log.debug(error);
          })
          .finally(() => {})
      }
    }
  }
</script>
