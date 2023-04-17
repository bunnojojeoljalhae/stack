function replyFormChange() {
  const element = document.getElementById("reply");
  element.innerHTML = `<div class="reply-music-wrap"> 
        <div class="reply-music"> 
            <div class="thumnail">검색결과 선택한 유튜브 동영상(뮤직이면 얼마나 좋아) 첨부</div> 
            
            <div class="reply-writer-info"> 
                <span>댓글 작성자 ID</span> 
                <span>댓글 작성일</span> 
            </div>

            <div class="music-info"> 
                <span>받아온 동영상의 제목(뮤직이면 노래 제목이겠지)</span> 
                <span>동영상 업로드 쥔장(뮤직이면 가수 이름이겠지)</span> 
            </div> 

            <div class="add-playlist"> 
                <button> 
                <svg id="plus-btn"
                xmlns="http://www.w3.org/2000/svg" 
                width="22" 
                height="22" 
                fill="currentColor" 
                class="bi bi-plus-square" 
                viewBox="0 0 16 16"> 
                <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" /> 
                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" /> 
                </svg> 
                </button> 
            </div> 
        </div> 
    </div>`;
}

function replyFormBefore() {
  const element = document.getElementById("reply");
  element.innerHTML = `  <div class="reply-music-wrap-before">
    <div class="reply-music-before">
      <div class="reply-writer-info-before">
        <div>
          <span>reply writer</span>
          <span>2022.11.21</span>
        </div>
      </div>
      <div class="music-info-before">
        <span>받아온 동영상의 제목(뮤직이면 노래 제목이겠지) - </span>
        <span>동영상 업로드 쥔장(뮤직이면 가수 이름이겠지)</span>
      </div>

      <div class="add-playlist-before">
        <button onclick="replyFormChange()">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="currentColor"
            class="bi bi-three-dots"
            viewBox="0 0 16 16"
          >
            <path
              d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"
            />
          </svg>
        </button>
      </div>
    </div>
  </div>`;
}
