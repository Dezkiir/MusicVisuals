// float buffer = width / 16;
// 		float rHeight = 40;
// 		float leftBorder = width / 5.67f;
// 		for(int i = 0; i < tasks.size(); i++)
// 		{
// 			fill(255);
// 			float y = map(i, 0, tasks.size(), 2 * buffer, height - buffer);
// 			text(tasks.get(i).getTask(), buffer * 2, y);
// 			noStroke();
// 			float color = map(i, 0, tasks.size(), 0, 255);
// 			fill(color, 255, 255);
	
// 			float BottomRect = map(tasks.get(i).getStart(), 1, 30, 180, 760);
// 			float RightRect = map(tasks.get(i).getEnd(), 1, 30, 180, 760);
// 			float LeftRect = RightRect - BottomRect;
// 			rect(BottomRect, y - rHeight / 2, LeftRect, rHeight);
// 		}