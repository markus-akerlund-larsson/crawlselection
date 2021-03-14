using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class CardDisplay : MonoBehaviour, IBeginDragHandler, IEndDragHandler, IDragHandler
{
    public Card card;
    public new Text name;
    public Text symbols;
    public Text text;
    public GameObject UI;

    public Vector3? originalPosition = null;
    private bool dragging = false;

    // Start is called before the first frame update
    void Start()
    {

    }

    private void Awake()
    {

    }

    string Symbols(string symbol, int count)
    {
        string res = "";
        for (int i = count; i > 0; --i)
        {
            res += symbol + "\n";
        }
        return res;
    }

    // Update is called once per frame
    void Update()
    {
        name.text = card.name;
        symbols.text =
            Symbols("F", card.fightSymbols) +
            Symbols("M", card.moveSymbols) +
            Symbols("S", card.scoutSymbols) +
            Symbols("R", card.restSymbols) +
            Symbols("P", card.prepareSymbols) +
            Symbols("E", card.examineSymbols);
        text.text = card.text;
    }

    public bool Targeted()
    {
        return card.targeted;
    }

    public void OnBeginDrag(PointerEventData eventData)
    {
        dragging = true;
        originalPosition = transform.position;
        GetComponent<CanvasGroup>().blocksRaycasts = false;
    }

    public void OnDrag(PointerEventData eventData)
    {

        var UISystem = UI.GetComponent<UISystem>();
        var targetingArrow = UISystem.targetingArrow.GetComponent<Pointer>();
        if (Targeted())
        {
            this.transform.position += new Vector3(0.05f * eventData.delta.x, 0.05f * eventData.delta.y, 0);
            UISystem.targetingArrow.transform.position = (Vector3)originalPosition;
            targetingArrow.visible = true;
            var dir = (eventData.position - (Vector2)originalPosition);
            targetingArrow.length = dir.magnitude;
            targetingArrow.direction = dir;
        }
        else
        {
            this.transform.position += new Vector3(0.2f * eventData.delta.x, 0.7f * eventData.delta.y, 0);
            targetingArrow.visible = false;
        }
        

    }

    public void FixedUpdate()
    {
        if (!dragging && originalPosition != null)
        {
            Vector3 distance = (Vector3)(originalPosition - transform.position);
            transform.position += 0.3f * distance;
        }

    }

    public void OnEndDrag(PointerEventData eventData)
    {
        dragging = false;
        GetComponent<CanvasGroup>().blocksRaycasts = true;
        var UISystem = UI.GetComponent<UISystem>();
        var targetingArrow = UISystem.targetingArrow.GetComponent<Pointer>();
        targetingArrow.visible = false;
    }
}
