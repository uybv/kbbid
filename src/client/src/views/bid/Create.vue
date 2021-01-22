<template>
  <div class="bid-create">
    <b-row>
      <b-col cols="12">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 class="h2">New Bid</h1>
          <b-button-toolbar>
            <b-button-group>
              <b-button :to="{ name: 'bid-list' }">Back</b-button>
            </b-button-group>
          </b-button-toolbar>
        </div>
        <InputForm id="0" :readOnly="false" />
      </b-col>
    </b-row>
  </div>
</template>

<script>
import InputForm from '../../components/bid/InputForm.vue';
export default {
  components: {
    InputForm
  }
}
</script>
