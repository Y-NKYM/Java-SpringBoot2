$(function() {
//	$('#deleteOkBtn').click(function(){
//		$('#deleteBtn').trigger('click');
//	})
	
	
	//テーブルの行をクリックしたときの処理
	//id=userListのtbodyタグのtrタグ（特定のテーブルの行）がクリックされた時
	$('#userList tbody tr').on('click', function(){
		//全ての行の選択状態を解除（テーブル行がクリックされた時、他からtable-activeを消す）
		$('#userList tbody tr').removeClass('table-active');
		//'this'とはクリックされた行。その<tr>タグに対し'table-active'クラスを追加。
		$(this).addClass('table-active');
		
		//更新ボタンと削除ボタンの活性化
		$('#updateBtn').removeAttr('disabled');
		$('#deleteDummyBtn').removeAttr('disabled');
		
		editSelectedLoginId($(this));
	});
});

//@param <tr> クリックされた行を引数として受け取る。
function editSelectedLoginId(row){
	//<tr>タグ内から<td>タグ情報を探し、ループ処理を持って、
	row.find('td').each(function(){
		//'this'とは<td>を回しているため各<td>のこと。その各<td>のid属性の値を取得。
		var columnId = $(this).attr('id');
		//id値が'loginId_'から始まる場合
		if(columnId.startsWith('loginId_')){
			//id=selectedLoginIdのタグ要素（form内hidden要素）に値を保管。
			//'this'とは<td>のこと。その記述文（つまりloginIdの値）を保管。
			$('#selectedLoginId').val($(this).text());
			//（念のため）強制的にループから抜ける処理を記述。
			return false;
		}
	});
};