/*---------------------------------------------------
 youtube api 사용
---------------------------------------------------*/
/*
let tag = document.createElement("script");

tag.src = "https://www.youtube.com/iframe_api";
let firstScriptTag = document.getElementsByTagName("script")[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

let player;
*/

/*
function onYoutubeIframeAPIReady() {
  player = new YT.player("player", {
    videoId: "Ncs_wip3hkU",
    width: "560",
    height: "349",
    playerVars: {
      modestbranding: 1,
      autoplay: 0,
      controls: 1,
      showinfo: 0,
      rel: 0,
      loop: 0,
    },
    events: {
      onReady: onPlayerReady,
      onStateChange: onPlyaerStateChange,
    },
  });
}

function onPlayerReady(event) {
  event.target.playVideo();
}

function onPlyaerStateChange() {
  if (player.getPlayerState() == 1) console.log("playing");
  else if (player.getPlayerState() == 2) console.log("pause");
}
*/
