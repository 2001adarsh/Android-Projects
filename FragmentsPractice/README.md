## Notes:
Fragments don’t subclass the "ontext" class. Therefore you have to use the "getActivity()" method to get the parent activity.




### Implementation:

```
public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rssitem_detail,
                container, false);
        return view;
    }

    public void setText(String text) {
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        view.setText(text);
    }
}
```

Steps :
1.
```
FragmentManager fragmentManager = getSupportFragmentManager();
FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
```
2.
```
// Create new fragment and transaction
Fragment newFragment = new ExampleFragment();
FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
transaction.replace(R.id.fragment_container, newFragment);  //or can use add();
transaction.addToBackStack(null);

// Commit the transaction
transaction.commit();
```
3.The order in which you add changes to a FragmentTransaction doesn't matter, except:
-> You must call commit() last.
-> If you're adding multiple fragments to the same container, then the order in which you add them determines the order they appear in      the view hierarchy.


4. For each fragment transaction, you can apply a transition animation, by calling setTransition() before you commit.
