<template>
  <div id="app">
    <router-view />
    <vue-particles
      class="index-bg"
      color="#fbc2eb"
      :particleOpacity="0.7"
      :particlesNumber="90"
      shapeType="triangle"
      :particleSize="5"
      linesColor="#fbc2eb"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.4"
      :linesDistance="150"
      :moveSpeed="3"
      :hoverEffect="true"
      hoverMode="grab"
      :clickEffect="true"
      clickMode="push">
      </vue-particles>
  </div>
</template>

<style lang="scss">
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
</style>
