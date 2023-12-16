const int Len = 40;

struct Golf {
    char fullname[Len];
    int handicap;
};

// non-interactive version:
//  function sets golf structure to provided name, handicap
//  using values passed as arguments to the function
void setgolf(Golf& g, const char* name, int hc);

// interactive version:
// function solicits name and handicap from user
// and sets the members of f to the values entered
// returns 1 if name is entered, 0 if name is empty string
int setgolf(Golf& g);

// function resets handicap to new value
void handicap(Golf& g, int hc);

// function displays contents of golf structure
void showgolf(const Golf& g);